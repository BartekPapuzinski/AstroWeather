package com.example.astroweather;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static double latitude = 51.75;
    public static double longitude = 19.49;
    public static int updateiterator=15;
    public static List<String> miasta = new ArrayList<>();
    public static int index=0;
    public static int numberofCitys=0;
    public static  String units ="&units=metric";// imperial dla F, metric dla C
    public static String jednostki=" C";
    public static String jednostki2=" m/s";
    //weatherPart

    //f1
    public static String name;
    public static String lat;
    public static String lon;
    public static String temp;
    public static String pressure;
    public static String description;
    public static  String image;

    //f2
    public static String speed;
    public static String kierunek;
    public static String humidity;
    public static String visibility;

    //f3
    public static  String jutro;
    public static  String tempday1;
    public static  String pressureday1;
    public static  String pojutro;
    public static  String tempday2;
    public static  String pressureday2;
    public static  String popojutro;
    public static  String tempday3;
    public static  String pressureday3;
}
