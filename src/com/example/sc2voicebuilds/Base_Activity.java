package com.example.sc2voicebuilds;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Base_Activity extends Activity {

	Intent i;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}
	
    @Override
public boolean onOptionsItemSelected(MenuItem item)
{
	switch (item.getItemId())
			{
		case R.id.home:
			Log.i("MENU", "Home Clicked");
			i = new Intent(this, MainActivity.class);
			this.startActivity(i);
			return true;
			
		case R.id.about:
			i = new Intent(this, About.class);
			this.startActivity(i);
			return true;
			
		case R.id.supportus:
			Log.i("MENU", "Support Us Clicked");
			i = new Intent(this, coming_soon.class);
			this.startActivity(i);
			return true;

		default:
			return super.onOptionsItemSelected(item);
			}
}
}
