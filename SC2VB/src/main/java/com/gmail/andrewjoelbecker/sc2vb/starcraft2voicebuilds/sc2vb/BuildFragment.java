package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by drew on 3/22/15.
 */
public class BuildFragment extends Fragment{
    MyBuild build;
    ArrayAdapter myarrayAdapter;
    ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_build, container, false);

        build = new MyBuild(1);
        build.addNode("SCV", 1, 5);
        build.addNode("MARINE", 1, 6);

        myarrayAdapter = new CustomAdapter(getActivity().getBaseContext(), R.layout.row, build.display);

        lv = (ListView)v.findViewById(R.id.listView);
        lv.setAdapter(myarrayAdapter);

        return v;
    }

}
