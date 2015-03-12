package com.example.sc2voicebuilds;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by drew on 3/11/15.
 */
public class UploadBuilds extends Base_Activity {
    TextView output;
    Spinner s;
    String [] races;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_builds);

        races = new String[3];
        s = (Spinner) findViewById(R.id.spinner1);
        races[0] = "Terran";
        races[1] = "Protoss";
        races[2] = "Zerg";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, races);
        s.setAdapter(adapter);

        output = (TextView) findViewById(R.id.outputTextView);
        if(!isNetworkConnected()){
            Toast.makeText(getBaseContext(), "Uh oh, it appears you aren't connected to the internet", Toast.LENGTH_LONG).show();
            output.setText("Error Connecting to Network");
        }
        else{
            // Enable Local Datastore.
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "v9D4hN8qNtXWTE4z4aNOHZsXkhBlVW29Iucw1Ll9", "bw5EcOR0neExSVWiMzrd8xdj1sqeSAmTSWlnZTdC");


            ParseObject testObject = new ParseObject("Build");
            testObject.put("Name", "BuildName");
            testObject.put("Race", "Terran");
            testObject.addAllUnique("Entity", Arrays.asList("SCV", "SCV", "Supply Depot", "Barracks"));
            Log.e("PARSE.COM", "DOWNLOAD STARTED");
            testObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Log.e("PARSE.COM","FAILED" + e.getMessage());
                    }
                    else
                    {
                        Log.e("PARSE.COM","SUCCESS");
                    }
                }
            });
        }
    }
/*
    public void constructBuild(String in, String race) {


        Scanner construct = new Scanner(datax);
        picker = 0;
        placeholder = 0;
        while (construct.hasNextLine()) {

            if (!readingBuild) {

                if (construct.nextLine().equals(in)) {
                    readingBuild = true;
                }

            }

            if (readingBuild) {
                String z = construct.nextLine();
                if (z != "end") {

                    if (picker == 0) {
                        constructedBuild += z + "\n";
                        displayArray[mytemp] = z + "\t";
                        placeholder++;
                        picker++;
                    } else if (picker == 1) {
                        constructedBuildMinutes += z + "\n";
                        displayArray[mytemp] += z + ":";
                        picker++;
                    } else if (picker == 2) {
                        constructedBuildSeconds += z + "\n";
                        displayArray[mytemp] += z;
                        mytemp++;
                        picker = 0;
                    }


                }

                if (z.equals("end")) {
                    readingBuild = false;
                }

            }

        }
    }

    public void loadBuild(View v) {
        datax = "";
        try {
            fIn = openFileInput("terranBuilds.dat");
            if (fIn != null) {
                InputStreamReader isr = new InputStreamReader(fIn);
                BufferedReader buffreader = new BufferedReader(isr);

                String readString = buffreader.readLine();
                while (readString != null) {
                    datax = datax + readString + "\n";
                    readString = buffreader.readLine();
                }
                isr.close();
                //here
            }

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        // Toast.makeText(getBaseContext(), datax, Toast.LENGTH_LONG).show();
        if (fIn != null) {
            intx = 0;
            tmp = 1;
            Scanner scan = new Scanner(datax);

            while (scan.hasNextLine()) {
                if (scan.nextLine().equals("end")) {
                    intx++;

                }
            }

            buildName = new String[intx];

            Scanner scan2 = new Scanner(datax);
            int i = 0;

            buildName[0] = scan2.nextLine();

            while (scan2.hasNextLine()) {
                if (scan2.nextLine().equals("end")) {
                    String z = Integer.toString(i);
                    String x = Integer.toString(intx);

                    if (i != intx - 1) {
                        buildName[tmp] = scan2.nextLine();
                        tmp++;
                        i++;
                    }

                }
            }

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Select Build");
            b.setItems(buildName, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    switch (which) {

                        case 0:
                            btn.setVisibility(View.VISIBLE);
                            txt.setText(buildName[0]);
                            txt.setVisibility(View.VISIBLE);
                            load.setVisibility(View.INVISIBLE);
                            constructBuild(buildName[0]);
                            break;

				/*
				 * case 1: int i = which; btn.setVisibility(View.VISIBLE);
				 * txt.setText(buildName[1]); txt.setVisibility(View.VISIBLE);
				 * load.setVisibility(View.INVISIBLE); break;


                        default:

                            btn.setVisibility(View.VISIBLE);
                            txt.setText(buildName[which]);
                            txt.setVisibility(View.VISIBLE);
                            load.setVisibility(View.INVISIBLE);
                            constructBuild(buildName[which]);
                            break;

                    }
                }

            });

            b.show();
        }
        else{
            Toast.makeText(getBaseContext(), "No builds found!", Toast.LENGTH_LONG).show();
        }

    }*/

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