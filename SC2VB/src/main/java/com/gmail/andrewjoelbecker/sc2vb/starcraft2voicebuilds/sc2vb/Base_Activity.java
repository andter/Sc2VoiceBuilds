package com.gmail.andrewjoelbecker.sc2vb.starcraft2voicebuilds.sc2vb;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Class: Base_Activity.java
 * Function: This class extends the ActionBarActivity and its primary focus is as a menu class which all other classes
 * extend from.  The Base_Activity simply displays a menu which users can click items to view other activities/classes
*/
public class Base_Activity extends ActionBarActivity {

    Intent i;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                i = new Intent(this, MainActivity.class);
                this.startActivity(i);
                return true;

            case R.id.about:
                i = new Intent(this, AboutActivity.class);
                this.startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

