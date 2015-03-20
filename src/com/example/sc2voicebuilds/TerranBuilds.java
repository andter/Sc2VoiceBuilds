package com.example.sc2voicebuilds;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TerranBuilds extends Base_Activity {
	String datax, buildName[], t, soundString, constructedBuild = "", constructedBuildMinutes = "", constructedBuildSeconds = "" , soundArray[], temprary, displayArray[];
	int intx, n = 0, tmp, selected, number = -1, minutes = 0, picker, soundArrayMinutes[], soundArraySeconds[], aNumber = 0, placeholder, mytemp = 0, BuildFin = 0, totalSec = 0, current=0;
	Boolean studying = true, readingBuild = false;
	TextView txt;
    MediaPlayer complete;
    FileInputStream fIn;
	Button btn, button2, button3, startBtn;
	ImageButton load;
	private Handler handler;
	private boolean Running = false;
	Runnable runnable;
	TextView textfield, textfield2, display, minutesDisplay, secondsDisplay;
	TextView blah, seconds, mins;
	DecimalFormat formatter;
	ArrayAdapter myarrayAdapter;
	ListView lv;
    View oldView, newView;
	DecimalFormat f;
	
	
	//copy
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terran_builds);

        displayArray = new String[50];
        for (int x = 0; x < 50; x++)
        {
            displayArray[x] = "";
        }
	}

    public void setupBuild(){
        Bundle bundle = new Bundle();
        bundle.putStringArray("soundArray", soundArray);
        bundle.putIntArray("soundArraySeconds", soundArraySeconds);
        bundle.putIntArray("soundArrayMinutes", soundArrayMinutes);
        bundle.putStringArray("displayArray", displayArray);
        TerranFragment frag = new TerranFragment();
        frag.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.placeholder, frag, "TerranFragment");
        transaction.commit();


    }


	// accepts a buildname as a string and constructs the build
	public void constructBuild(String in) {


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
					
					if (picker == 0)
					{
						constructedBuild += z + "\n";
						displayArray[mytemp] = z + "\t";
						placeholder++;
						picker++;
					}
					else if(picker == 1)
					{
						constructedBuildMinutes += z + "\n";
						displayArray[mytemp] += z + ":";
						picker++;
					}
					
					else if(picker == 2)
					{
						constructedBuildSeconds += z + "\n";
						displayArray[mytemp] += z;
						mytemp++;
						picker = 0;
					}
					
					
				} 
				
				if(z.equals("end"))
				{
					readingBuild = false;
				}

			}

		}

		soundArray = new String [placeholder];
		soundArrayMinutes = new int [placeholder-1];
		soundArraySeconds = new int [placeholder-1];
		String suckmyD = "";
		Scanner scanSounds = new Scanner(constructedBuild);
		Scanner scanMinutes = new Scanner(constructedBuildMinutes);
		Scanner scanSeconds = new Scanner(constructedBuildSeconds);
		
		int SS = 0;
		int y = 0;
		int yx = 0;
		
		while (scanMinutes.hasNextLine()) {
			String xyz = scanMinutes.nextLine();
			int xyzx = Integer.parseInt(xyz);
			soundArrayMinutes[y] = xyzx;
			
			y++;
		}

		while(scanSeconds.hasNextLine()){
			String xyz = scanSeconds.nextLine();
			int xyzx = Integer.parseInt(xyz);
			soundArraySeconds[yx] = xyzx;
			String bleh = Integer.toString(soundArraySeconds[yx]);
			yx++;
		}

		
		while (scanSounds.hasNextLine()) {
			
			soundString = scanSounds.nextLine();
			if (soundString.equals("Barracks"))
			{
				soundArray[SS] = "barracks";
			}
			
			else if (soundString.equals("Factory"))
			{
				soundArray[SS] = "factory";
			}
			
			else if (soundString.equals("Starport"))
			{
				soundArray[SS] = "starport";
			}
			
			else if (soundString.equals("Supply Depot"))
			{
				soundArray[SS] = "supplydepot";
			}
			
			else if (soundString.equals("Command Center"))
			{
				soundArray[SS] = "commandcenter";
			}
			
			else if (soundString.equals("Orbital Command"))
			{
				soundArray[SS] = "orbitalcommand";
			}
			
			else if (soundString.equals("Planetary Fortress"))
			{
				soundArray[SS] = "planetaryfortress";
			}
			
			else if (soundString.equals("Refinery"))
			{
				soundArray[SS] = "refinery";
			}
			
			else if (soundString.equals("Engineering Bay"))
			{
				soundArray[SS] = "engineeringbay";
			}
			
			else if (soundString.equals("Armory"))
			{
				soundArray[SS] = "armory";
			}
			
			else if (soundString.equals("Ghost Academy"))
			{
				soundArray[SS] = "ghostacademy";
			}
			
			else if (soundString.equals("Missile Turret"))
			{
				soundArray[SS] = "missileturret";
			}
			
			else if (soundString.equals("Sensor Tower"))
			{
				soundArray[SS] = "sensortower";
			}
			
			else if (soundString.equals("Reactor"))
			{
				soundArray[SS] = "reactor";
			}
			else if (soundString.equals("Tech lab"))
			{
				soundArray[SS] = "techlab";
			}
			
			else if (soundString.equals("SCV"))
			{
				soundArray[SS] = "scv";
			}
			else if (soundString.equals("Marine"))
			{
				soundArray[SS] = "marine";
			}
			else if (soundString.equals("Marauder"))
			{
				soundArray[SS] = "marauder";
			}
			else if (soundString.equals("Reaper"))
			{
				soundArray[SS] = "reaper";
			}
			else if (soundString.equals("Ghost"))
			{
				soundArray[SS] = "ghost";
			}
			else if (soundString.equals("Hellion"))
			{
				soundArray[SS] = "hellion";
			}
			else if (soundString.equals("Tank"))
			{
				soundArray[SS] = "tank";
			}
			else if (soundString.equals("Widow Mine"))
			{
				soundArray[SS] = "widowmine";
			}
			else if (soundString.equals("Thor"))
			{
				soundArray[SS] = "thor";
			}
			else if (soundString.equals("Banshee"))
			{
				soundArray[SS] = "banshee";
			}
			else if (soundString.equals("Viking"))
			{
				soundArray[SS] = "viking";
			}
			else if (soundString.equals("Raven"))
			{
				soundArray[SS] = "raven";
			}
			else if (soundString.equals("BattleCruiser"))
			{
				soundArray[SS] = "battlecruiser";
			}
			else if (soundString.equals("2 workers on Gas"))
			{
				soundArray[SS] = "twoworkersongas";
			}
			
			else if (soundString.equals("3 workers on gas"))
			{
				soundArray[SS] = "threeworkersongas";
			}
			
			else if (soundString.equals("end"))
			{
				soundArray[SS] = "end";
			}
			
			else{
				soundArray[SS] = "none";
			}
			SS++;
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

                AlertDialog.Builder b = new Builder(this);
                b.setTitle("Select Build");
                b.setItems(buildName, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {

                            case 0:
                                constructBuild(buildName[0]);
                                setupBuild();
                                break;

				/*
				 * case 1: int i = which; btn.setVisibility(View.VISIBLE);
				 * txt.setText(buildName[1]); txt.setVisibility(View.VISIBLE);
				 * load.setVisibility(View.INVISIBLE); break;
				 */

                            default:
                                constructBuild(buildName[which]);
                                setupBuild();
                                /*
                                btn.setVisibility(View.VISIBLE);
                                txt.setText(buildName[which]);
                                txt.setVisibility(View.VISIBLE);
                                load.setVisibility(View.INVISIBLE);
                                constructBuild(buildName[which]);*/
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
}
