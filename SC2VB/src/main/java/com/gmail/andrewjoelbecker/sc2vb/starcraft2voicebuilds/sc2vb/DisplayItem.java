package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

/**
 * Class: DisplayItem.java
 * Function: Similar to the Node class, it is a singular item inside a build.  This item however is specifically
 * for display purposes and allows users to see what the build says in a neat format
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
