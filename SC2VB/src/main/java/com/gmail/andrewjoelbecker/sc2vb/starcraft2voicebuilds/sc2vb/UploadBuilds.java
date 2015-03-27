package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: UploadBuilds.java
 * Function: This class allows users to upload builds to the database via the parse API and connected database
 */
public class UploadBuilds extends Base_Activity {
    TextView output, buildTextView;
    Spinner s;
    String[] races;
    int numberOfRequests, requestsLeft, race;
    String selectedBuild, datax, email;
    FileInputStream fIn;
    static Boolean uploaded;
    ArrayList<String> buildNames = new ArrayList<String>();
    ArrayList<String> build = new ArrayList<String>();
    String allBuilds;

    TextView tv, nameView;
    Spinner spin;
    Button btn, btn2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_builds);

        AdView adView = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("839B631B02A7FF2AC27C272F634E7E3E")
                .build();

        adView.loadAd(adRequest);

        numberOfRequests = 4;
        requestsLeft = 5;
        races = new String[3];
        s = (Spinner) findViewById(R.id.spinner1);
        races[0] = "Terran";
        races[1] = "Protoss";
        races[2] = "Zerg";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, races);
        s.setAdapter(adapter);

        output = (TextView) findViewById(R.id.outputTextView);
        buildTextView = (TextView) findViewById(R.id.myBuild);

        if(!isNetworkConnected()){
            Toast.makeText(getBaseContext(), "Uh oh, it appears you aren't connected to the internet", Toast.LENGTH_LONG).show();
            output.setText("Error Connecting to Network");
        }
        else{
            //Enable all views
            LinearLayout linearDisplay = (LinearLayout)findViewById(R.id.linearDisplay);
            tv = (TextView) findViewById(R.id.textv);
            nameView = (TextView) findViewById(R.id.myBuild);
            spin = (Spinner) findViewById(R.id.spinner1);
            btn = (Button) findViewById(R.id.button1);
            btn2 = (Button) findViewById(R.id.button);

            linearDisplay.setVisibility(View.VISIBLE);

                Parse.initialize(this, "v9D4hN8qNtXWTE4z4aNOHZsXkhBlVW29Iucw1Ll9", "bw5EcOR0neExSVWiMzrd8xdj1sqeSAmTSWlnZTdC");




            email = getEmail(this);


        }
    }

    public void upload(View v){
        if(email == null){
            Toast.makeText(getBaseContext(), "Need a registered email associated to account", Toast.LENGTH_LONG).show();
        }
        else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
            query.whereEqualTo("Email", email);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                        addUser();
                        uploadBuild();
                        Toast.makeText(getBaseContext(), "Successfully Uploaded Build!", Toast.LENGTH_SHORT).show();
                    } else {
                        int request = object.getInt("Requests");
                        if (request > 0) {
                                requestsLeft = request - 1;
                                object.put("Requests", requestsLeft);
                                object.saveInBackground();
                                uploadBuild();
                        }
                        else{
                            Toast.makeText(getBaseContext(), "You can't upload any more builds at the present moment", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }

    public void addUser(){
        ParseObject userObject = new ParseObject("User");
        userObject.put("Email", email);
        userObject.put("Requests", numberOfRequests);
        userObject.saveInBackground();
    }

    public void notifyNumberOfBuilds(){
        Toast.makeText(getBaseContext(), "You can upload " + requestsLeft + " builds until reset", Toast.LENGTH_LONG).show();
    }
    public boolean uploadBuild(){
        uploaded = false;
        String object = "";
        String uploadedRace = "";
        if(race == 1){
            object = "Build";
            uploadedRace = "Terran";
        }
        else if(race == 2){
            object = "PBuild";
            uploadedRace = "Protoss";
        }
        else if(race == 3){
            object = "ZBuild";
            uploadedRace = "Zerg";
        }
        ParseObject testObject = new ParseObject(object);
        testObject.put("Name", "$" + selectedBuild);
        testObject.put("Race", uploadedRace);
        testObject.addAllUnique("Entity", build);
        Log.e("PARSE.COM", "DOWNLOAD STARTED");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getBaseContext(), "FAILED" + e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Successfully Uploaded Build!", Toast.LENGTH_SHORT).show();
                    notifyNumberOfBuilds();
                }
            }
        });
        Log.i("Uploaded", uploaded.toString());
        return uploaded;
    }

    public void selectBuild(View v) throws IOException {

        datax = "";
        int intx, tmp;
            if (s.getSelectedItemId() == 0) {
                fIn = openFileInput("terran.dat");
                race = 1;
            } else if (s.getSelectedItemId() == 1) {
                fIn = openFileInput("protoss.dat");
                race = 2;
            } else {
                fIn = openFileInput("zerg.dat");
                race = 3;
            }

            buildNames = new ArrayList<String>();
            allBuilds = "";
            FileInputStream fileInput = null;
            if (race != 0) {
                    if (race == 1) {
                        fileInput = openFileInput("terran.dat");
                    } else if (race == 2) {
                        fileInput = openFileInput("protoss.dat");
                    } else if (race == 3) {
                        fileInput = openFileInput("zerg.dat");
                    }

                    if (fileInput != null) {
                        InputStreamReader inputReader = new InputStreamReader(fileInput);
                        BufferedReader buffReader = new BufferedReader(inputReader);
                        String readString = buffReader.readLine();
                        while (readString != null) {
                            if (readString.contains("$")) {
                                buildNames.add(readString.substring(1));
                            }
                            allBuilds = allBuilds + readString + "\n";
                            readString = buffReader.readLine();
                        }
                        inputReader.close();

                        if (buildNames.size() > 0) {
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
                        } else {
                            Toast.makeText(getBaseContext(), "No Builds Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "No builds found!", Toast.LENGTH_LONG).show();
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

    static String getEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);

        if (account == null) {
            return null;
        } else {
            return account.name;
        }
    }

    public void initializeBuild(int i){
        selectedBuild = buildNames.get(i);
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
                                build.add(temp);
                            }
                        }
                    } else {
                        n++;
                    }
                }
            }
            readingBuild = false;
        }
        nameView.setText(selectedBuild);

    }

    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }
}

