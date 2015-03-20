package com.example.sc2voicebuilds;

/**
 * Created by drew on 3/19/15.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Display> {
    private ArrayList<Display> objects;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Display> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, null);
        }


        Display i = objects.get(position);

        if (i != null) {
            TextView nameView = (TextView) v.findViewById(R.id.itemName);
            TextView timeView = (TextView) v.findViewById(R.id.time);

            if (nameView != null){
                nameView.setText(i.getItem());
            }
            if (timeView != null){
                timeView.setText(i.getTime());
            }

        }

        return v;
    }
}