package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;

/* Class: MainActivity.java
*  Function: This class is the home screen for the application and is the gateway to all other activities
*/

public class MainActivity extends Base_Activity {

    private final String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView adView = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("839B631B02A7FF2AC27C272F634E7E3E")
                .build();

        adView.loadAd(adRequest);

        File file = new File("terran.dat");
        if(file.exists()){
            Toast.makeText(getBaseContext(), "Terran file exists", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "File doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }

    public void zergClick(View v)
    {
        Intent intent = new Intent(v.getContext(), BuildLoader.class);
        intent.putExtra("race", 3);
        v.getContext().startActivity(intent);
    }

    public void terranClick(View v)
    {
        Intent intent = new Intent(v.getContext(), BuildLoader.class);
        intent.putExtra("race", 1);
        v.getContext().startActivity(intent);

    }

    public void protossClick(View v)
    {
        Intent intent = new Intent(v.getContext(), BuildLoader.class);
        intent.putExtra("race", 2);
        v.getContext().startActivity(intent);
    }

    public void loadData(View v){
        Intent intent = new Intent(v.getContext(), UploadBuilds.class);
        v.getContext().startActivity(intent);
    }

    public void exit()
    {
        finish();
    }

    public void selectRace(View v)
    {
        final View d = v;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] races = {"Zerg", "Terran", "Protoss"};
        dialogBuilder.setTitle("Select a race");
        dialogBuilder.setItems(races, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which){
                //	Toast.makeText(getApplicationContext(), races[which], Toast.LENGTH_SHORT).show();
                if(races[which].equals("Terran"))
                {
                    Intent intent = new Intent(d.getContext(), BuildCreator.class);
                    intent.putExtra("race", 1);
                    d.getContext().startActivity(intent);
                }

                if(races[which].equals("Zerg"))
                {
                    Intent intent = new Intent(d.getContext(), BuildCreator.class);
                    intent.putExtra("race", 3);
                    d.getContext().startActivity(intent);
                }

                if(races[which].equals("Protoss"))
                {
                    Intent intent = new Intent(d.getContext(), BuildCreator.class);
                    intent.putExtra("race", 2);
                    d.getContext().startActivity(intent);
                }
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

}
