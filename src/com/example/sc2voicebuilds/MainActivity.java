package com.example.sc2voicebuilds;


import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Base_Activity {

	private final String TAG = "Main Activity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}
	
	public void zergClick(View v)
	{
		Intent intent = new Intent(v.getContext(), BuildLoader.class);
        intent.putExtra("race", 3);
		v.getContext().startActivity(intent);
	}
	
	public void terranClick(View v)
	{
		Intent intent = new Intent(v.getContext(), BuildLoader.class);
        intent.putExtra("race", 1);
		v.getContext().startActivity(intent);
		
	}
	
	public void protossClick(View v)
	{
		Intent intent = new Intent(v.getContext(), BuildLoader.class);
        intent.putExtra("race", 2);
		v.getContext().startActivity(intent);
	}

    public void loadData(View v){
        Intent intent = new Intent(v.getContext(), UploadBuilds.class);
        v.getContext().startActivity(intent);
    }
	
	public void exit()
	{
		finish();
	}
	
	public void selectRace(View v)
	{
		final View d = v;
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		final String[] races = {"Zerg", "Terran", "Protoss"};
		dialogBuilder.setTitle("Select a race");
		dialogBuilder.setItems(races, new DialogInterface.OnClickListener(){
			
			public void onClick(DialogInterface dialog, int which){
			//	Toast.makeText(getApplicationContext(), races[which], Toast.LENGTH_SHORT).show();
				if(races[which] == "Terran")
				{
					Intent intent = new Intent(d.getContext(), BuildCreator.class);
                    intent.putExtra("race", 1);
					d.getContext().startActivity(intent);
				}
				
				if(races[which] == "Zerg")
				{
					Intent intent = new Intent(d.getContext(), BuildCreator.class);
                    intent.putExtra("race", 3);
					d.getContext().startActivity(intent);
				}
				
				if(races[which] == "Protoss")
				{
					Intent intent = new Intent(d.getContext(), BuildCreator.class);
                    intent.putExtra("race", 2);
					d.getContext().startActivity(intent);
				}
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
	}

}
