package com.example.astroweather;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;

import java.io.IOException;
import java.util.zip.Inflater;



public class CityInfo extends Fragment {

    LayoutInflater inflater;
    View view;
    TextView nazwa;
    TextView wspolrzedne;
    TextView temperatura;
    TextView cisnienie;
    TextView opis;
    ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cityinfo, container, false);
        this.inflater=inflater;
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
        image= view.findViewById(R.id.image);

    }

    public void go() {
        nazwa.setText(Config.name);
        wspolrzedne.setText(Config.lon +"   "+ Config.lat);
        temperatura.setText(Config.temp);
        cisnienie.setText(Config.pressure);
        opis.setText(Config.description);

        //image.setImageResource(getResources().getIdentifier("a01d.png", "drawable",inflater.getContext().getPackageName()));
    }



}
