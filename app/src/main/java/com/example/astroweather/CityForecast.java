package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CityForecast extends Fragment {


    View view;
    TextView nazwa;
    TextView jutro;
    TextView pojutro;
    TextView popojutro;
    TextView tempday1;
    TextView pressureday1;
    TextView tempday2;
    TextView pressureday2;
    TextView tempday3;
    TextView pressureday3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cityforecast, container, false);
        init();
        go();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        go();
    }

    public void init() {
        nazwa=view.findViewById(R.id.nazwa);

        jutro = view.findViewById(R.id.day1);
        pojutro = view.findViewById(R.id.day2);
        popojutro = view.findViewById(R.id.day3);

        tempday1=view.findViewById(R.id.temp1);
        tempday2=view.findViewById(R.id.temp2);
        tempday3=view.findViewById(R.id.temp3);

        pressureday1=view.findViewById(R.id.pressure1);
        pressureday2=view.findViewById(R.id.pressure2);
        pressureday3=view.findViewById(R.id.pressure3);
    }

    public void go(){

        nazwa.setText(Config.name);

        jutro.setText(Config.jutro);
        pojutro.setText(Config.pojutro);
        popojutro.setText(Config.popojutro);

        tempday1.setText(Config.tempday1);
        tempday2.setText(Config.tempday2);
        tempday3.setText(Config.tempday3);

        pressureday1.setText(Config.pressureday1);
        pressureday2.setText(Config.pressureday2);
        pressureday3.setText(Config.pressureday3);
    }

}
