package com.example.sc2voicebuilds;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.xml.datatype.Duration;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Menu;
import android.view.SurfaceView;
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
		
		myarrayAdapter = new ArrayAdapter<String>(this, R.layout.items, displayArray);
		lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(myarrayAdapter);
		f = new DecimalFormat("00");
		
		btn = (Button) findViewById(R.id.start);
		txt = (TextView) findViewById(R.id.name);
		load = (ImageButton) findViewById(R.id.imageButton1);
		textfield = (TextView) findViewById(R.id.secondsLabel);
		textfield2 = (TextView) findViewById(R.id.minutesLabel);
	//	display = (TextView) findViewById(R.id.display);
	//	minutesDisplay = (TextView) findViewById(R.id.minutesDisplay);
	//	secondsDisplay = (TextView) findViewById(R.id.secondsDisplay);
		blah = (TextView) findViewById(R.id.blah);
		mins = (TextView) findViewById(R.id.minutesLabel);
		seconds = (TextView) findViewById(R.id.secondsLabel);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		startBtn = (Button) findViewById(R.id.start);

		handler = new Handler();
		runnable = new Runnable() {

			@Override
			public void run() {
				while (Running) {
					try {
						Thread.sleep(727);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						@Override
						public void run() {
							if (number == 59) {
								number = 0;
								minutes++;
	    						formatter = new DecimalFormat("00");
								textfield2.setText(String.valueOf(formatter.format(minutes)));
								textfield.setText(String.valueOf(formatter.format(number)));

							}
    						formatter = new DecimalFormat("00");
							number++;
							textfield.setText(String.valueOf(formatter.format(number)));
							checkForUpdates(number, minutes);

						}
					});
				}
			}

		};
		// new Thread(runnable).start();

	}

	public void startTimer(View v) {
        int secondSize = soundArraySeconds.length;
        totalSec = soundArraySeconds[secondSize-1];
        if (!Running) {
            blah.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);
            seconds.setVisibility(View.VISIBLE);
            mins.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            //	display.setVisibility(View.VISIBLE);
            //	minutesDisplay.setVisibility(View.VISIBLE);
            //	secondsDisplay.setVisibility(View.VISIBLE);
            startBtn.setVisibility(View.INVISIBLE);
            new Thread(runnable).start();
            Running = true;
        }

	}
	
	public void checkForUpdates(int num, int min)
	{
        int colorPointer = Color.parseColor("#8030ACD6");
        oldView = lv.getChildAt(current-1);
        newView = lv.getChildAt(current);
        if (aNumber > placeholder -2){
            if (num >= totalSec+2) {
                if (newView != null){
                    newView.setBackgroundColor(colorPointer);
                }
                if (current > 0){
                    oldView.setBackgroundColor(Color.TRANSPARENT);
                }
                if (complete == null) {
                    Toast.makeText(getBaseContext(), "Build Finished", Toast.LENGTH_LONG).show();
                    complete = MediaPlayer.create(this, R.raw.buildfinished);
                    complete.start();
                }
                stopTimer();
            }
        }
        else {
           if (newView != null){
               newView.setBackgroundColor(colorPointer);
              if (current > 0) {
                  oldView.setBackgroundColor(Color.TRANSPARENT);
              }
           }
            String aaa = Integer.toString(num);
            String bleh = Integer.toString(soundArrayMinutes[aNumber]);
            int convert = Integer.parseInt(bleh);
            String bleh2 = Integer.toString(soundArraySeconds[aNumber]);
            int convert2 = Integer.parseInt(bleh2);

		/*
	    final MediaPlayer armory = MediaPlayer.create(this, R.raw.armory);
	    final MediaPlayer banshee = MediaPlayer.create(this, R.raw.banshee);
	    final MediaPlayer barracks = MediaPlayer.create(this, R.raw.barracks);
	    final MediaPlayer battlecruiser = MediaPlayer.create(this, R.raw.battlecruiser);
	    final MediaPlayer commandcenter = MediaPlayer.create(this, R.raw.commandcenter);
	    final MediaPlayer engineeringbay = MediaPlayer.create(this, R.raw.engineeringbay);
	    final MediaPlayer factory = MediaPlayer.create(this, R.raw.factory);
	    final MediaPlayer ghost = MediaPlayer.create(this, R.raw.ghost);
	    final MediaPlayer ghostacademy = MediaPlayer.create(this, R.raw.ghostacademy);
	    final MediaPlayer hellion = MediaPlayer.create(this, R.raw.hellion);
	    final MediaPlayer marine= MediaPlayer.create(this, R.raw.marine);
	    final MediaPlayer missileturret = MediaPlayer.create(this, R.raw.missileturret);
	    final MediaPlayer orbitalcommand = MediaPlayer.create(this, R.raw.orbitalcommand);
	    final MediaPlayer planetaryfortress = MediaPlayer.create(this, R.raw.planetaryfortress);
	    final MediaPlayer raven = MediaPlayer.create(this, R.raw.raven);
	    final MediaPlayer reactor = MediaPlayer.create(this, R.raw.reactor);
	    final MediaPlayer reaper = MediaPlayer.create(this, R.raw.reaper);
	    final MediaPlayer refinery = MediaPlayer.create(this, R.raw.refinery);
	    final MediaPlayer scv = MediaPlayer.create(this, R.raw.scv);
	    final MediaPlayer sensortower = MediaPlayer.create(this, R.raw.sensortower);
	    final MediaPlayer starport = MediaPlayer.create(this, R.raw.starport);
	    final MediaPlayer supplydepot = MediaPlayer.create(this, R.raw.supplydepot);
	    final MediaPlayer tank = MediaPlayer.create(this, R.raw.tank);
	    final MediaPlayer thor = MediaPlayer.create(this, R.raw.thor);
	    final MediaPlayer threeworkers = MediaPlayer.create(this, R.raw.threeworkers);
	    final MediaPlayer twoworkers = MediaPlayer.create(this, R.raw.twoworkers);
	    final MediaPlayer viking = MediaPlayer.create(this, R.raw.viking);
	    final MediaPlayer widowmine = MediaPlayer.create(this, R.raw.widowmine);*/


            if (convert == soundArrayMinutes[aNumber]) {
                if (convert2 == num) {
                    String variable;

                    if (soundArray[aNumber].equals("barracks")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.barracks);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("supplydepot")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.supplydepot);
                        mp.start();
                    }


                    if (soundArray[aNumber].equals("armory")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.armory);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("banshee")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.banshee);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("battlecruiser")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.battlecruiser);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("commandcenter")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.commandcenter);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("engineeringbay")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.engineeringbay);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("factory")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.factory);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("ghost")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ghost);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("ghostacademy")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ghostacademy);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("hellion")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.hellion);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("marine")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.marine);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("missileturret")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.missileturret);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("orbitalcommand")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.orbitalcommand);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("planetaryfortress")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.planetaryfortress);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("raven")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.raven);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("reactor")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.reactor);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("reaper")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.reaper);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("refinery")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.refinery);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("scv")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.scv);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("sensortower")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sensortower);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("starport")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.starport);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("tank")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.tank);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("techlab")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.techlab);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("thor")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.thor);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("viking")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.viking);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("widowmine")) {
                        final MediaPlayer mp = MediaPlayer.create(this, R.raw.widowmine);
                        mp.start();
                    }
           /* if (aNumber != placeholder - 2) {
                //temprary = soundArray[aNumber];
                aNumber++;}*/
                    aNumber++;
                    current++;
                }
            }
        }
	    }

	    
			//	mp = MediaPlayer.create(getBaseContext(), R.raw.barracks);
			//	mp.start();
			
		//}

	public void stopTimer(View v) {
		Running = false;
		number--;
	}

	public void stopTimer() {
		Running = false;
		number--;
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

		//after placing things into their respected strings, display strings
		//soundArray = new int[placeholder];
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
		//	Toast.makeText(getBaseContext(), xyz, Toast.LENGTH_SHORT).show();
			
			y++;
		}

		while(scanSeconds.hasNextLine()){
			String xyz = scanSeconds.nextLine();
			int xyzx = Integer.parseInt(xyz);
			soundArraySeconds[yx] = xyzx;
			String bleh = Integer.toString(soundArraySeconds[yx]);
		//	Toast.makeText(getBaseContext(), bleh, Toast.LENGTH_SHORT).show();
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
		//	suckmyD += soundArray[SS];
			SS++;
		}
		
	//	Toast.makeText(getBaseContext(), suckmyD, Toast.LENGTH_LONG).show();
		myarrayAdapter.notifyDataSetChanged();
	//	display.setText(constructedBuild);
	//	minutesDisplay.setText(constructedBuildMinutes);
	//	secondsDisplay.setText(constructedBuildSeconds);
		

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
				 */

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

	}
}
