package com.example.astroweather;

import com.astrocalculator.AstroCalculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sun extends Fragment {


    View view;
    TextView wschod = null;
    TextView azymutW =null;
    TextView zachod =null;
    TextView azymutz =null;
    TextView zmierzch =null;
    TextView swit =null;
    AstroCalculator astroCalculator;
    AstroCalculator.Location location;

    public Sun() {
        // Required empty public constructor
    }

    public void setup(){

        wschod = view.findViewById(R.id.wschod);
        azymutW = view.findViewById(R.id.azymutW);
        zachod = view.findViewById(R.id.zachod);
        azymutz = view.findViewById(R.id.azymutZ);
        zmierzch = view.findViewById(R.id.zmierzch);
        swit = view.findViewById(R.id.swit);



        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy HH mm ss");
        String[] currentDate = simpleDateFormat.format(date).split(" ");
        AstroDateTime astroDateTime = new AstroDateTime();
        astroDateTime.setTimezoneOffset(1);
        astroDateTime.setDaylightSaving(true);
        astroDateTime.setDay(Integer.valueOf(currentDate[0]));
        astroDateTime.setMonth(Integer.valueOf(currentDate[1]));
        astroDateTime.setYear(Integer.valueOf(currentDate[2]));
        astroDateTime.setHour(Integer.valueOf(currentDate[3]));
        astroDateTime.setMinute(Integer.valueOf(currentDate[4]));
        astroDateTime.setSecond(Integer.valueOf(currentDate[5]));
        location = new AstroCalculator.Location(51.75,19.49);
        astroCalculator = new AstroCalculator(astroDateTime,location);

    }

    public void update(){
        String[] temp;
        temp= astroCalculator.getSunInfo().getSunrise().toString().split(" ");
        wschod.setText(temp[1]);
        double temp1= astroCalculator.getSunInfo().getAzimuthRise();
        azymutW.setText(String.valueOf(temp1).substring(0,6));
        temp= astroCalculator.getSunInfo().getSunset().toString().split(" ");
        zachod.setText(temp[1]);
        temp1 = astroCalculator.getSunInfo().getAzimuthSet();
        azymutz.setText(String.valueOf(temp1).substring(0,6));
        temp= astroCalculator.getSunInfo().getTwilightMorning().toString().split(" ");
        swit.setText(temp[1]);
        temp= astroCalculator.getSunInfo().getTwilightEvening().toString().split(" ");
        zmierzch.setText(temp[1]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sun, container, false);
        setup();
        update();
        return view;
    }

}
