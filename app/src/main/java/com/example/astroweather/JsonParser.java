package com.example.astroweather;


import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.Toast;
import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonParser extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects){
        JSONObject obj = null;
        String miasto=Config.miasto;
        String units=Config.units;
        String appkey="&appid=6cbb4d139a4ccc8e697a07432728584c";
        String url="https://api.openweathermap.org/data/2.5/weather?q="+miasto +appkey
                + units;
        String url2="https://api.openweathermap.org/data/2.5/forecast?q="+miasto+appkey+units;
        try {
            System.out.println(url);
            obj = readJsonFromUrl(url);
            if(obj.getString("cod").equals("404")){

            }
            else {
                Config.lon = obj.getJSONObject("coord").getString("lon");
                Config.lat = obj.getJSONObject("coord").getString("lat");
                Config.temp = obj.getJSONObject("main").getString("temp")+Config.jednostki;
                Config.pressure = obj.getJSONObject("main").getString("pressure") +" hPa";
                Config.name = obj.getString("name");
                Config.speed = obj.getJSONObject("wind").getString("speed") + " m/s";
                try{
                Config.kierunek = obj.getJSONObject("wind").getString("deg");
                }catch (Exception e){}
                Config.humidity = obj.getJSONObject("main").getString("humidity")+ " %";
                Config.visibility =String.valueOf(Double.parseDouble(obj.getString("visibility"))/100)+" %";


                JSONArray arr = obj.getJSONArray("weather");
                Config.description = arr.getJSONObject(0).getString("description");
                Config.image= arr.getJSONObject(0).getString("icon");

                System.out.println(obj.getJSONObject("coord").getString("lon"));
                obj = readJsonFromUrl(url2);
                arr = obj.getJSONArray("list");
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String currentdate = simpleDateFormat.format(date);
                int index = 0;
                for (int i = 0; i < arr.length(); i++) {
                    if (arr.getJSONObject(i).getString("dt_txt").split(" ")[1].equals("12:00:00")) {
                        if (arr.getJSONObject(i).getString("dt_txt").split(" ")[0].equals(currentdate)) {
                            index = i + 8;
                            break;
                        } else {
                            index = i;
                            break;
                        }

                    }
                }
                Config.jutro = arr.getJSONObject(index).getString("dt_txt").split(" ")[0];
                Config.tempday1 = arr.getJSONObject(index).getJSONObject("main").getString("temp")+Config.jednostki;
                Config.pressureday1 = arr.getJSONObject(index).getJSONObject("main").getString("pressure").substring(0,4)+"hPa";

                Config.pojutro = arr.getJSONObject(index + 8).getString("dt_txt").split(" ")[0];
                Config.tempday2 = arr.getJSONObject(index + 8).getJSONObject("main").getString("temp")+Config.jednostki;
                Config.pressureday2 = arr.getJSONObject(index).getJSONObject("main").getString("pressure").substring(0,4)+"hPa";

                Config.popojutro = arr.getJSONObject(index + 16).getString("dt_txt").split(" ")[0];
                Config.tempday3 = arr.getJSONObject(index + 16).getJSONObject("main").getString("temp")+Config.jednostki;
                Config.pressureday3 = arr.getJSONObject(index).getJSONObject("main").getString("pressure").substring(0,4)+"hPa";

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}
