package com.example.astroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Moon extends Fragment {

    boolean loop = true;
    View view;
    TextView wschod = null;
    TextView zachod = null;
    TextView now = null;
    TextView pelnia = null;
    TextView faza = null;
    TextView dzien = null;
    AstroCalculator astroCalculator;
    AstroCalculator.Location location;

    public Moon() {
        // Required empty public constructor
    }

    public void setup() {

        wschod = view.findViewById(R.id.wschod);
        zachod = view.findViewById(R.id.zachod);
        now = view.findViewById(R.id.now);
        pelnia = view.findViewById(R.id.pelnia);
        faza = view.findViewById(R.id.faza);
        dzien = view.findViewById(R.id.dzien);
    }

    public void update() {
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
        location = new AstroCalculator.Location(Config.latitude, Config.longitude);
        astroCalculator = new AstroCalculator(astroDateTime, location);
        String[] temp;
        temp = astroCalculator.getMoonInfo().getMoonrise().toString().split(" ");
        wschod.setText(temp[1]);
        temp = astroCalculator.getMoonInfo().getMoonset().toString().split(" ");
        zachod.setText(temp[1]);
        temp = astroCalculator.getMoonInfo().getNextNewMoon().toString().split(" ");
        now.setText(temp[0]);
        temp = astroCalculator.getMoonInfo().getNextFullMoon().toString().split(" ");
        pelnia.setText(temp[0]);
        double temp1 = astroCalculator.getMoonInfo().getIllumination();
        faza.setText(String.valueOf(temp1).substring(0, 6));
        double temp2 = astroCalculator.getMoonInfo().getAge();
        dzien.setText(String.valueOf(temp2).substring(0, 6));

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_moon, container, false);
        setup();
        go();
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        loop=false;
    }

    @Override
    public void onResume() {
        super.onResume();
        loop=true;
        go();
    }


    public void go() {
        new Thread(new Runnable() {

            public void run() {
                int upiterator = Config.updateiterator;
                while (loop) {
                    if (Config.updateiterator < upiterator) {
                        upiterator = 1;
                        getActivity().runOnUiThread(new Runnable() {

                            @Override public void run() {
                                update();
                                Toast.makeText(getContext(), "ref", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    upiterator++;
                }
            }
        }).start();

    }

}
