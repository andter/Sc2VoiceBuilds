package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;

/**
 * Created by drew on 3/11/15.
 */
public class UploadBuilds extends Base_Activity {
    TextView output, buildTextView;
    Spinner s;
    String[] races, constructedBuild;
    int myTemp, numberOfRequests, requestsLeft;
    String selectedBuild, datax, race, email;
    FileInputStream fIn;
    String[] buildName;
    static Boolean uploaded;

    TextView tv, tv2;
    Spinner spin;
    Button btn, btn2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_upload_builds);
/*
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
            tv = (TextView) findViewById(R.id.textv);
            tv2 = (TextView) findViewById(R.id.myBuild);
            spin = (Spinner) findViewById(R.id.spinner1);
            btn = (Button) findViewById(R.id.button1);
            btn2 = (Button) findViewById(R.id.button);

            tv.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            spin.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            /*
                Parse.enableLocalDatastore(this);
                Parse.initialize(this, "v9D4hN8qNtXWTE4z4aNOHZsXkhBlVW29Iucw1Ll9", "bw5EcOR0neExSVWiMzrd8xdj1sqeSAmTSWlnZTdC");*/



        /*
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
        ParseObject testObject = new ParseObject("Build");
        testObject.put("Name", selectedBuild);
        testObject.put("Race", race);
        testObject.addAllUnique("Entity", Arrays.asList(constructedBuild));
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


    public void constructBuild(String in) {
        int picker, placeholder;
        myTemp = 0;
        boolean readingBuild = false;
        Scanner construct = new Scanner(datax);
        picker = 0;
        placeholder = 0;
        String unparsedBuild = "";
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
                        unparsedBuild += z + "{";
                        placeholder++;
                        picker++;
                    } else if (picker == 1) {
                        unparsedBuild += z + ":";
                        picker++;
                    } else if (picker == 2) {
                        unparsedBuild += z + "}\n";
                        picker = 0;
                        myTemp++;
                    }
                }

                if (z.equals("end")) {
                    readingBuild = false;
                }
            }
            constructedBuild = new String[myTemp];
            Scanner scan = new Scanner(unparsedBuild);
            int temp = 0;

            for(int x = 0; x < myTemp; x++) {
                constructedBuild[temp] = scan.nextLine();
                temp++;
            }

        }
    }

    public void selectBuild(View v) {
        datax = "";
        int intx, tmp;
        try {
            if(s.getSelectedItemId() == 0) {
                fIn = openFileInput("terranBuilds.dat");
                race = "Terran";
            }
            else if (s.getSelectedItemId() == 1) {
                fIn = openFileInput("protossBuilds.dat");
                race = "Protoss";
            }
            else{
                fIn = openFileInput("zergBuilds.dat");
                race = "Zerg";
            }
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
                            selectedBuild = buildName[0];
                            buildTextView.setText("Build: " + selectedBuild);
                            constructBuild(selectedBuild);
                            break;





                        default:
                            selectedBuild = buildName[which];
                            buildTextView.setText("Build: " + selectedBuild);
                            constructBuild(selectedBuild);
                            break;

                    }
                }

            });

            b.show();
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

    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }*/
    }
}
