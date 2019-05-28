package com.example.astroweather;

import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // For this example, only two pages
    static final int NUM_ITEMS = 2;

    ViewPager mPager;
    SlidePagerAdapter mPagerAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Instantiate a ViewPager and a PagerAdapter. */
        mPager = (ViewPager) findViewById(R.id.ViewPager);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

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

        final EditText longitude =dialog.findViewById(R.id.longitude);
        final EditText latitude =dialog.findViewById(R.id.latitude);
        Button enter = dialog.findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                if(longitude.getText() !=null && latitude!=null){
                    Config.latitude=Double.valueOf(latitude.getText().toString());
                    Config.longitude=Double.valueOf(longitude.getText().toString());
                    Toast.makeText(getApplicationContext(), Config.latitude+" "+Config.longitude, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}