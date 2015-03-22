package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by drew on 3/19/15.
 */
public class BuildLoader extends Base_Activity {

    MyBuild build;
    int race;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_loader);

        Intent i = getIntent();
        race = i.getIntExtra("race", 0);
        build = new MyBuild(race);
        Toast.makeText(getBaseContext(), "Race " + race, Toast.LENGTH_SHORT).show();
    }

}