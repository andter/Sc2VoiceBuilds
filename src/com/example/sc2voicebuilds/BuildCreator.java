package com.example.sc2voicebuilds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;

/**
 * Created by drew on 3/19/15.
 */
public class BuildCreator extends Activity {
    MyBuild build;
    int race, seconds, minutes;
    Spinner structuresSpinner, unitsSpinner;
    String [] structuresArray, unitsArray;
    Handler handler;
    Runnable runnable;
    Boolean Running;
    TextView secondsTV, minutesTV;
    CustomAdapter myarrayAdapter;
    ListView lv;
    DecimalFormat formatter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_creator);

        Intent i = getIntent();

        race = i.getIntExtra("race", 0);
        build = new MyBuild(race);
        Toast.makeText(getBaseContext(), "Race " + i.getIntExtra("race", 0), Toast.LENGTH_SHORT).show();

        if(race == 1){
            terranSetup();
        }
        else if(race == 2){
            protossSetup();
        }
        else if (race == 3){
            zergSetup();
        }
        else{
            Toast.makeText(getBaseContext(), "Error Retrieving Race!", Toast.LENGTH_LONG).show();
        }
    }

    //Perform basic zerg Setup
    public void zergSetup(){
        build = new MyBuild(race);
        setupThread();
        setupZergUI();
    }

    //Perform basic protoss Setup
    public void protossSetup(){
        build = new MyBuild(race);
        setupThread();
        setupProtossUI();
    }

    //Perform basic terran Setup
    public void terranSetup(){
        build = new MyBuild(race);
        setupThread();
        setupTerranUI();
    }

    //Set up UI components for zerg race
    public void setupZergUI(){
        TextView racePrompt = (TextView)findViewById(R.id.createRacePrompt);
        racePrompt.setText("Create Zerg Build");
    }

    //Set up UI components for protoss race
    public void setupProtossUI(){
        TextView racePrompt = (TextView)findViewById(R.id.createRacePrompt);
        racePrompt.setText("Create Protoss Build");

        myarrayAdapter = new CustomAdapter(this, R.layout.row, build.display);

        lv = (ListView)findViewById(R.id.buildListView);
        lv.setAdapter(myarrayAdapter);

        structuresSpinner = (Spinner) findViewById(R.id.structuresSpinner);
        structuresArray = new String[17];
        structuresArray[0] = "Structures";
        structuresArray[1] = "Gateway";
        structuresArray[2] = "Robotics Facility";
        structuresArray[3] = "Stargate";
        structuresArray[4] = "Pylon";
        structuresArray[5] = "Nexus";
        structuresArray[6] = "Cybernetics Core";
        structuresArray[7] = "Fleet Beacon";
        structuresArray[8] = "Assimilator";
        structuresArray[9] = "Forge";
        structuresArray[10] = "Fleet Beacon";
        structuresArray[11] = "Twilight Council";
        structuresArray[12] = "Photon Cannon";
        structuresArray[13] = "Templar Archives";
        structuresArray[14] = "Warpgate Research";
        structuresArray[15] = "Robotics Bay";
        structuresArray[16] = "Dark shrine";
        ArrayAdapter structuresAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, structuresArray);
        structuresSpinner.setAdapter(structuresAdapter);

        //Setup Unit Spinner
        unitsSpinner = (Spinner) findViewById(R.id.unitsSpinner);
        unitsArray = new String[19];
        unitsArray[0] = "Units---";
        unitsArray[1] = "Probe";
        unitsArray[2] = "Zealot";
        unitsArray[3] = "Stalker";
        unitsArray[4] = "Sentry";
        unitsArray[5] = "Observer";
        unitsArray[6] = "Immortal";
        unitsArray[7] = "Warp Prism";
        unitsArray[8] = "Colossus";
        unitsArray[9] = "Pheonix";
        unitsArray[10] = "Void Ray";
        unitsArray[11] = "High Templar";
        unitsArray[12] = "Dark Templar";
        unitsArray[13] = "Archon";
        unitsArray[14] = "Carrier";
        unitsArray[15] = "Mother Ship Core";
        unitsArray[16] = "MotherShip";
        unitsArray[17] = "2 Workers on Gas";
        unitsArray[18] = "3 workers on gas";

        ArrayAdapter unitsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitsArray);
        unitsSpinner.setAdapter(unitsAdapter);

    }

    //Set up UI components for terran race
    public void setupTerranUI(){
        //Setup Structure Spinner
        TextView racePrompt = (TextView)findViewById(R.id.createRacePrompt);
        racePrompt.setText("Create Terran Build");

        myarrayAdapter = new CustomAdapter(this, R.layout.row, build.display);

        lv = (ListView)findViewById(R.id.buildListView);
        lv.setAdapter(myarrayAdapter);

        structuresSpinner = (Spinner) findViewById(R.id.structuresSpinner);
        structuresArray = new String[16];
        structuresArray[0] = "Structures";
        structuresArray[1] = "Barracks";
        structuresArray[2] = "Factory";
        structuresArray[3] = "Starport";
        structuresArray[4] = "Supply Depot";
        structuresArray[5] = "Command Center";
        structuresArray[6] = "Orbital Command";
        structuresArray[7] = "Planetary Fortress";
        structuresArray[8] = "Refinery";
        structuresArray[9] = "Engineering Bay";
        structuresArray[10] = "Armory";
        structuresArray[11] = "Ghost Academy";
        structuresArray[12] = "Missile Turret";
        structuresArray[13] = "Sensor Tower";
        structuresArray[14] = "Reactor";
        structuresArray[15] = "Tech lab";
        ArrayAdapter structuresAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, structuresArray);
        structuresSpinner.setAdapter(structuresAdapter);

        //Setup Unit Spinner
        unitsSpinner = (Spinner) findViewById(R.id.unitsSpinner);
        unitsArray = new String[15];
        unitsArray[0] = "Units";
        unitsArray[1] = "SCV";
        unitsArray[2] = "Marine";
        unitsArray[3] = "Marauder";
        unitsArray[4] = "Reaper";
        unitsArray[5] = "Ghost";
        unitsArray[6] = "Hellion";
        unitsArray[7] = "Tank";
        unitsArray[8] = "Widow Mine";
        unitsArray[9] = "Thor";
        unitsArray[10] = "Banshee";
        unitsArray[11] = "Viking";
        unitsArray[12] = "Raven";
        unitsArray[13] = "BattleCruiser";
        unitsArray[14] = "2 Workers on Gas";
        unitsArray[14] = "3 workers on gas";

        ArrayAdapter unitsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitsArray);
        unitsSpinner.setAdapter(unitsAdapter);

    }

    public void updateUI(){
        myarrayAdapter.notifyDataSetChanged();
    }

    public void start(View v){
        if (!Running) {
            new Thread(runnable).start();
            Running = true;
        }
    }

    public void stop(View v){
        Running = false;
        seconds--;
    }

    public void setupThread(){
        secondsTV = (TextView)findViewById(R.id.secondsOutput);
        minutesTV = (TextView)findViewById(R.id.minutesOutput);
        Running = false;

        handler = new Handler();
        formatter = new DecimalFormat("00");
        runnable = new Runnable(){

            @Override
            public void run(){
                while(Running){
                    try{
                        Thread.sleep(727);}
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        @Override
                        public void run(){
                            if (seconds == 60)
                            {
                                seconds = 0;
                                minutes++;
                                minutesTV.setText(String.valueOf(formatter.format(minutes)));

                            }
                            seconds++;
                            secondsTV.setText(String.valueOf(formatter.format(seconds)));

                        }
                    });
                }
            }


        };
    }

    public void addStructure(View v){
        int position = structuresSpinner.getSelectedItemPosition();
        if(position > 0 && (seconds > 0 || minutes > 0)){
            build.addNode(structuresArray[position], minutes, seconds);
            updateUI();
        }
    }

    public void addUnit(View v){
        int position = unitsSpinner.getSelectedItemPosition();
        if(position > 0 && (seconds > 0 || minutes > 0)) {
            build.addNode(unitsArray[position], minutes, seconds);
            updateUI();
        }
    }

}