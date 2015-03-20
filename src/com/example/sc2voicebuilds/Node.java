package com.example.sc2voicebuilds;

/**
 * Created by drew on 3/19/15.
 */
public class Node {
    String item;
    int minutes;
    int seconds;

    public Node(String item, int minutes, int seconds){
        this.item = item;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Node getNode(){
        return this;
    }

    @Override
    public String toString(){
        return item + "{" + minutes + ":" + seconds + "}";
    }
}

