package com.example.sc2voicebuilds;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by drew on 3/13/15.
 */
public class Creator_Activity extends Activity {

    Intent i;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.creator_bar, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.homeButton:
                Log.i("MENU", "Home Clicked");
                i = new Intent(this, MainActivity.class);
                this.startActivity(i);
                return true;

            case R.id.save:
                Log.i("MENU", "SAVE CLICKED");
            //    this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

