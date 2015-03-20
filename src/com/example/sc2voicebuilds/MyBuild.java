package com.example.sc2voicebuilds;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * Created by drew on 3/19/15.
 */
public class MyBuild {
    private ArrayList<Node> build = new ArrayList<Node>();
    ArrayList<Display> display = new ArrayList<Display>();
    int race, size = 0;
    DecimalFormat formatter;

    //Create a new build with a race; Terran = 1, Protoss = 2, Zerg = 3
    public MyBuild(int race){
        this.race = race;
        formatter = new DecimalFormat("00");
    }

    public void addNode(String item, int minutes, int seconds){
        display.add(new Display(item,String.valueOf(formatter.format(minutes)) + ":" + String.valueOf(formatter.format(seconds))));
        build.add(new Node(item, minutes, seconds));
        size++;
    }

    public void removeNode(int index){
        display.remove(index);
        build.remove(index);
        size = size-1;
    }

    public int getSize(){
        return build.size();
    }
}