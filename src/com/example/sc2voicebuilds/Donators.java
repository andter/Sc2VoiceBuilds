package com.example.sc2voicebuilds;

import java.sql.DriverManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Donators extends Base_Activity {
	Thread thrd1;
	Thread thrd2;
	Thread thrd3;
	Connection con = null;
	String zz = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donators);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Creating the connection variable
		// param1 = how what and where the database is that we want to use
		// param2 = DB username
		// param3 = DB password
	/*	Connection connect = null;
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://198.57.247.240/drewbee_scvb", "drewbee_user",
					"Supertoilet0");
			PreparedStatement statement = connect
					.prepareStatement("SELECT id, name, race, amount FROM donators");
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				zz = result.getString(1) + " " + result.getString(2)
						+ result.getString(3) + result.getString(4);
				Toast.makeText(getBaseContext(), zz, Toast.LENGTH_LONG).show();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

	}

	
	public void myClick(View v)
	{

		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();

		    }

		   
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://198.57.247.240/drewbee_scvb", "drewbee_user",
					"Supertoilet0");
			PreparedStatement statement = connect
					.prepareStatement("SELECT id, name, race, amount FROM donators");
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				zz = result.getString(1) + " " + result.getString(2)
						+ result.getString(3) + result.getString(4);
				Toast.makeText(getBaseContext(), zz, Toast.LENGTH_LONG).show();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}
		}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}

}
