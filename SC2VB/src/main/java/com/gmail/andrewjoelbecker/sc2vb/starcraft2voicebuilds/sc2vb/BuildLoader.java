package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by drew on 3/19/15.
 */
public class BuildLoader extends Base_Activity {
    ArrayList<String> buildNames;
    ArrayList<String> build;
    int race;
    String allBuilds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_loader);

        Intent i = getIntent();
        race = i.getIntExtra("race", 0);

        if(race == 1){
            TextView tv = (TextView)findViewById(R.id.buildType);
            tv.setText("TerranBuild:");
        }
        if(race == 2){
            TextView tv = (TextView)findViewById(R.id.buildType);
            tv.setText("ProtossBuild:");
        }
        if(race == 3){
            TextView tv = (TextView)findViewById(R.id.buildType);
            tv.setText("ZergBuild:");
        }
    }

    public void initializeBuild(int i){
        String title = buildNames.get(i);
        build = new ArrayList<String>();
        Scanner scan = new Scanner(allBuilds);

        boolean readingBuild = true, read = true;
        int n = 0;
        while(readingBuild) {
            while (scan.hasNext()) {
                if (scan.nextLine().contains("$")) {
                    if (n == i) {
                        while (scan.hasNext() && read) {
                            String temp = scan.nextLine();
                            if (temp.contains("$")) {
                                read = false;
                            } else {
                                build.add(temp + "\n");
                            }
                        }
                    } else {
                        n++;
                    }
                }
            }
            readingBuild = false;
        }
        displayBuild(title);
    }

    public void displayBuild(String t){
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("build", build);
            bundle.putString("buildName", t);
            bundle.putInt("race", race);
            BuildFragment frag = new BuildFragment();
            frag.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.placeholder, frag, "");
            transaction.commit();
    }

    public void displayDownloadables(View v) {
        if (isNetworkConnected()) {
            Bundle bundle = new Bundle();
            bundle.putInt("race", race);
            DownloadFragment frag = new DownloadFragment();
            frag.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.placeholder, frag, "");
            transaction.commit();
        }
        else{
            Toast.makeText(getBaseContext(), "It appears you aren't connected to the internet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadBuild(View v) {
        buildNames = new ArrayList<String>();
        allBuilds = "";
        FileInputStream fileInput = null;
        if (race != 0) {
        try {
                if (race == 1) {
                    fileInput = openFileInput("terran.dat");
                } else if (race == 2) {
                    fileInput = openFileInput("protoss.dat");
                } else if (race == 3) {
                    fileInput = openFileInput("zerg.dat");
                }

            if(fileInput != null) {
                InputStreamReader inputReader = new InputStreamReader(fileInput);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String readString = buffReader.readLine();
                while(readString != null){
                    if(readString.contains("$")){
                        buildNames.add(readString.substring(1));
                    }
                    allBuilds = allBuilds + readString + "\n";
                    readString = buffReader.readLine();
                }
                inputReader.close();

                if(buildNames.size() > 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Select Build");
                    builder.setItems(buildNames.toArray(new String[buildNames.size()]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            initializeBuild(which);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    Toast.makeText(getBaseContext(), "No Builds Found", Toast.LENGTH_SHORT).show();
                }
            }


            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else{
            Toast.makeText(getBaseContext(), "Error retrieving race", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

}