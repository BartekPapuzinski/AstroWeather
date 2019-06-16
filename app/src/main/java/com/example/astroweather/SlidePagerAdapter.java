package com.example.astroweather;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.example.astroweather.MainActivity.NUM_ITEMS;

public class SlidePagerAdapter extends FragmentPagerAdapter {
        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /*
             * IMPORTANT: This is the point. We create a RootFragment acting as
             * a container for other fragments
             */
            if (position == 0)
                return new Sun();
            else if(position==1)
                return new Moon();
            else if(position==2)
                return new CityInfo();
            else if(position==3)
                return new CityWind();
            else
                return new CityForecast();
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
}




