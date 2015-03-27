package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

/**
 * Class: Node.java
 * A Node is a singular item inside a build, it contains an item and some time in which it is located inside the Build
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

    @Override
    public String toString(){
        return (item + "{" + minutes + ":" + seconds + "}");
    }
}

