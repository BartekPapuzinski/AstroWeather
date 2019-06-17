package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;

import java.io.IOException;

public class CityInfo extends Fragment {

    View view;
    TextView nazwa;
    TextView wspolrzedne;
    TextView temperatura;
    TextView cisnienie;
    TextView opis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cityinfo, container, false);
        init();
        go();
        return view;
    }

    public void init(){

        nazwa=view.findViewById(R.id.nazwa);
        wspolrzedne=view.findViewById(R.id.wspolrzedne);
        temperatura=view.findViewById(R.id.temperatura);
        cisnienie=view.findViewById(R.id.cisnienie);
        opis=view.findViewById(R.id.opis);

    }

    public void go() {



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        nazwa.setText(Config.name);
        wspolrzedne.setText(Config.lon +"   "+ Config.lat);
        temperatura.setText(Config.temp);
        cisnienie.setText(Config.pressure);
        opis.setText(Config.description);
    }



}
