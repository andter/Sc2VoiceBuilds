package com.example.sc2voicebuilds;

import android.app.Fragment;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.DecimalFormat;

/**
 * Created by drew on 3/15/15.
 */
public class TerranFragment extends Fragment {
   ArrayAdapter myarrayAdapter;
    ListView lv;
    TextView txt, minutesTV, secondsTv;
    DecimalFormat f;
    String displayArray[], soundArray[];
    Button startButton, stopButton;
    Handler handler;
    Runnable runnable;
    Boolean running = false;
    int number, minutes, totalSec = 0, aNumber = 0, current = 0, placeholder = 0, soundArraySeconds[], soundArrayMinutes[];
    View oldView, newView;
    MediaPlayer complete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_build, container, false);

        Bundle bundle = this.getArguments();
        soundArray = bundle.getStringArray("soundArray");
        soundArrayMinutes = bundle.getIntArray("soundArrayMinutes");
        soundArraySeconds = bundle.getIntArray("soundArraySeconds");
        String temp = "";
        for(int x =0; x < soundArraySeconds.length; x++){
            temp += soundArraySeconds[x];
        }
        Log.i("SoundArrayMinutes", temp);
        displayArray = bundle.getStringArray("displayArray");

        //Initialize Views
        initializeThread();
        txt = (TextView) view.findViewById(R.id.name);
        minutesTV = (TextView) view.findViewById(R.id.minutesLabel);
        secondsTv = (TextView) view.findViewById(R.id.secondsLabel);


        myarrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.items, displayArray);
        lv = (ListView) view.findViewById(R.id.itemListView);
        lv.setAdapter(myarrayAdapter);
        f = new DecimalFormat("00");

        stopButton = (Button) view.findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stopTimer();
            }
        });

        startButton = (Button) view.findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        return view;

        //End Initialize Views
    }

    public void initializeThread(){
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                while (running) {
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
                                minutesTV.setText(String.valueOf(f.format(minutes)));
                                secondsTv.setText(String.valueOf(f.format(number)));

                            }
                            f = new DecimalFormat("00");
                            number++;
                            secondsTv.setText(String.valueOf(f.format(number)));
                            checkForUpdates(number, minutes);

                        }
                    });
                }
            }

        };
        // new Thread(runnable).start();

    }


    public void startTimer() {
        int secondSize = soundArraySeconds.length;
        totalSec = soundArraySeconds[secondSize - 1];
        if (!running) {
            new Thread(runnable).start();
            running = true;
        }
    }

    public void stopTimer() {
        running = false;
        number--;
    }

    public void checkForUpdates(int sec, int min)
    {
        int colorPointer = Color.parseColor("#8030ACD6");
        oldView = lv.getChildAt(current-1);
        newView = lv.getChildAt(current);
        newView.setBackgroundColor(colorPointer);

        if (aNumber >= soundArraySeconds.length){
            oldView.setBackgroundColor(Color.TRANSPARENT);
            if (sec == totalSec+2) {
                if (newView != null){
                    newView.setBackgroundColor(colorPointer);
                }
                if (complete == null) {
                    Toast.makeText(getActivity().getBaseContext(), "Build Finished", Toast.LENGTH_LONG).show();
                    complete = MediaPlayer.create(getActivity().getBaseContext(), R.raw.buildfinished);
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
            if (min == soundArrayMinutes[aNumber]) {
                if (sec == soundArraySeconds[aNumber]) {
                    Log.i("EVENT", "Seconds = " + soundArraySeconds[aNumber] + "\nMinutes = " + soundArrayMinutes[aNumber]);

                    if (soundArray[aNumber].equals("barracks")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.barracks);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("supplydepot")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.supplydepot);
                        mp.start();
                    }


                    if (soundArray[aNumber].equals("armory")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.armory);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("banshee")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.banshee);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("battlecruiser")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.battlecruiser);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("commandcenter")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.commandcenter);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("engineeringbay")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.engineeringbay);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("factory")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.factory);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("ghost")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.ghost);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("ghostacademy")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.ghostacademy);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("hellion")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.hellion);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("marine")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.marine);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("missileturret")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.missileturret);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("orbitalcommand")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.orbitalcommand);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("planetaryfortress")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.planetaryfortress);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("raven")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.raven);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("reactor")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.reactor);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("reaper")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.reaper);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("refinery")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.refinery);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("scv")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.scv);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("sensortower")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.sensortower);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("starport")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.starport);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("tank")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.tank);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("techlab")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.techlab);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("thor")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.thor);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("viking")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.viking);
                        mp.start();
                    }

                    if (soundArray[aNumber].equals("widowmine")) {
                        final MediaPlayer mp = MediaPlayer.create(getActivity().getBaseContext(), R.raw.widowmine);
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
}
