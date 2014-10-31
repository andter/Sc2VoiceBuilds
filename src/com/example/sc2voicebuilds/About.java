package com.example.sc2voicebuilds;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class About extends Base_Activity {




@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_about);
}

public void displaySafehouse(View v)
{
	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.safehousegaming.com"));
	startActivity(browserIntent);
}
}


