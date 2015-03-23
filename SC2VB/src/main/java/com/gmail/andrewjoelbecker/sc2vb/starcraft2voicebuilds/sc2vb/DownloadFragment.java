package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by drew on 3/23/15.
 */
public class DownloadFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_download, container, false);
        Bundle bundle = this.getArguments();


        return v;
    }
}
