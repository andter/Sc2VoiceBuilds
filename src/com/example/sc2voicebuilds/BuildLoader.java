package com.example.sc2voicebuilds;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by drew on 3/19/15.
 */
public class BuildLoader extends Activity {

    MyBuild build;
    int race;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_loader);

        Intent i = getIntent();
        build = new MyBuild(i.getIntExtra("race", 0));
        Toast.makeText(getBaseContext(), "Race " + i.getIntExtra("race", 0), Toast.LENGTH_SHORT).show();
        race = i.getIntExtra("race", 0);
    }

    public void loadBuild(View v){
            Bundle bundle = new Bundle();
        /*    bundle.putStringArray("soundArray", soundArray);
            bundle.putIntArray("soundArraySeconds", soundArraySeconds);
            bundle.putIntArray("soundArrayMinutes", soundArrayMinutes);
            bundle.putStringArray("displayArray", displayArray);*/
            LoadFragment frag = new LoadFragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.placeholder, frag, "LoadFragment");
            transaction.commit();
    }

}
