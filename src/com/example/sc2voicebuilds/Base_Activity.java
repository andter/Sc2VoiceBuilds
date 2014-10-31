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
	
	private final String TAG = "Main Activity";
	String inputString;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}
	


public boolean onOptionsItemSelected(MenuItem item)
{
	switch (item.getItemId())
			{
		case R.id.home:
			Log.i(TAG, "Home Clicked");
			Intent intent = new Intent(this, MainActivity.class);
			this.startActivity(intent);
			return true;
			
		case R.id.about:
			Intent in = new Intent(this, About.class);
			this.startActivity(in);
			return true;
			
		case R.id.supportus:
			Log.i(TAG, "Support Us Clicked");
			Intent i = new Intent(this, coming_soon.class);
			this.startActivity(i);
			return true;
			
		case R.id.save:
			Log.i(TAG, "Save Clicked");
			AlertDialog.Builder editalert = new AlertDialog.Builder(this);
			
			editalert.setTitle("Save");
			editalert.setMessage("Enter Name of Build to Save");


			final EditText input = new EditText(this);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			        LinearLayout.LayoutParams.FILL_PARENT,
			        LinearLayout.LayoutParams.FILL_PARENT);
			input.setLayoutParams(lp);
			editalert.setView(input);

			editalert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int whichButton) {
			    	inputString = new String(input.getText().toString());
			    	Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();
			    }
			});
			
			editalert.show();
			return true;
			
			
		default:
			return super.onOptionsItemSelected(item);
			}
}
}
