package com.example.astroweather;


import android.os.AsyncTask;
import android.widget.Toast;
import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonParser extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects){
        JSONObject obj = null;
        String miasto=Config.miasto;
        String units=Config.units;
        String url="https://api.openweathermap.org/data/2.5/weather?q="+miasto +"&appid=6cbb4d139a4ccc8e697a07432728584c"
                + units;
        try {
            System.out.println(url);
            obj = readJsonFromUrl(url);

        Config.lon= obj.getJSONObject("coord").getString("lon");
        Config.lat=obj.getJSONObject("coord").getString("lat");
        Config.temp=obj.getJSONObject("main").getString("temp");
        Config.pressure=obj.getJSONObject("main").getString("pressure");
        Config.name=obj.getString("name");
        Config.speed=obj.getJSONObject("wind").getString("speed")+"km/h";
        Config.kierunek=obj.getJSONObject("wind").getString("deg");
        Config.humidity=obj.getJSONObject("main").getString("humidity");
        Config.visibility=obj.getString("visibility");


        JSONArray arr = obj.getJSONArray("weather");
        Config.description=arr.getJSONObject(0).getString("description");

        System.out.println(obj.getJSONObject("coord").getString("lon"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return null;
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
