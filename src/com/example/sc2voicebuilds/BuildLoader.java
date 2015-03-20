package com.example.sc2voicebuilds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by drew on 3/19/15.
 */
public class BuildLoader extends Activity {

    MyBuild build;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_creator);

        Intent i = getIntent();
        build = new MyBuild(i.getIntExtra("race", 0));
        Toast.makeText(getBaseContext(), "Race " + i.getIntExtra("race", 0), Toast.LENGTH_SHORT).show();
    }

}