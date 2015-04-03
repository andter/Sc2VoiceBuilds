package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

/**
 * Created by drew on 4/3/15.
 */
public class TitleItem {
    int position;
    String item;

    public TitleItem(int pos, String t){
        item = t;
        position = pos;
    }

    public String getItem(){
        return item;
    }

    public int getPosition(){
        return position;
    }
}