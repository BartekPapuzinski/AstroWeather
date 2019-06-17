package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CityWind extends Fragment {

    View view;
    TextView sila;
    TextView kierunek;
    TextView wilgotnosc;
    TextView widocznosc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_citywind, container, false);
        init();
        go();
        return view;
    }

    public void init(){
        sila=view.findViewById(R.id.sila);
        kierunek=view.findViewById(R.id.kierunek);
        wilgotnosc=view.findViewById(R.id.wilgotnosc);
        widocznosc=view.findViewById(R.id.widocznosc);
    }

    public void  go(){
        sila.setText(Config.speed);
        kierunek.setText(Config.kierunek);
        wilgotnosc.setText(Config.humidity);
        widocznosc.setText(Config.visibility);
    }
}
