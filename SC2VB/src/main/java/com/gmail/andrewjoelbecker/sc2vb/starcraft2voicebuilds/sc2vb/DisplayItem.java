package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

/**
 * Created by drew on 3/21/15.
 */
public class DisplayItem {
    String item;
    String time;

    public DisplayItem(String i, String t){
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
