package com.example.astroweather;

import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // For this example, only two pages
    static final int NUM_ITEMS = 2;

    boolean loop=true;
    TextView timer;
    ViewPager mPager;
    SlidePagerAdapter mPagerAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timer = findViewById(R.id.timer);
        /* Instantiate a ViewPager and a PagerAdapter. */
        mPager = (ViewPager) findViewById(R.id.ViewPager);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        clock();
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
        clock();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                setUpCustomDialog();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);

        final EditText longitude = dialog.findViewById(R.id.longitude);
        final EditText latitude = dialog.findViewById(R.id.latitude);
        Button enter = dialog.findViewById(R.id.enter);
        Spinner spiner = dialog.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spiner,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(this);
        enter.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                try {
                    if (Double.valueOf( longitude.getText().toString()) >-180 &&Double.valueOf( longitude.getText().toString()) <180 && Double.valueOf( latitude.getText().toString()) >-90 && Double.valueOf( latitude.getText().toString()) <90) {
                        Config.latitude = Double.valueOf(latitude.getText().toString());
                        Config.longitude = Double.valueOf(longitude.getText().toString());
                        Toast.makeText(getApplicationContext(), Config.latitude + " " + Config.longitude, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }catch (Exception e){
                    //ignore--
                }
            }
        });
        dialog.show();
    }

    public void clock() {
        new Thread(new Runnable() {

            public void run() {

                while (loop) {

                    final Date date = new Date();
                    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    runOnUiThread(new Runnable() {

                        @Override public void run() {
                            timer.setText(String.valueOf(simpleDateFormat.format(date)));

                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                Config.updateiterator=15;
                Toast.makeText(parent.getContext(),String.valueOf(Config.updateiterator),Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Config.updateiterator=30;
                Toast.makeText(parent.getContext(),String.valueOf(Config.updateiterator),Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Config.updateiterator=45;
                Toast.makeText(parent.getContext(),String.valueOf(Config.updateiterator),Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Config.updateiterator=60;
                Toast.makeText(parent.getContext(),String.valueOf(Config.updateiterator),Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Config.updateiterator=90;
                Toast.makeText(parent.getContext(),String.valueOf(Config.updateiterator),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override public void onNothingSelected(AdapterView<?> parent) {

    }
}