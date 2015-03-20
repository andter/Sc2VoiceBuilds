package com.example.sc2voicebuilds;

import java.util.ArrayList;

/**
 * Created by drew on 3/19/15.
 */
public class Display {
    String item;
    String time;

    public Display(String i, String t){
        item = i;
        time = t;
    }

    public String getItem(){
        return item;
    }

    public String getTime(){
        return time;
    }
}
