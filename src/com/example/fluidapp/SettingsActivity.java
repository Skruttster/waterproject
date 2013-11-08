package com.example.fluidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class SettingsActivity extends Activity {

	/*
	 * This is the page where the user can alter his or her settings
	 * Settings such as gender, weight and if he or she is meant to drink extra water
	 */
	Intent intent;
	
	RadioGroup rbGroup;
	RadioButton rbWoman;
	RadioButton rbMan;
	
	
	TextView txtVWeight;
	EditText eTxtWeight;
	
	TextView txtVMedicin;
	EditText eTxtMedicin;
	
	Button btnSave;
	
	
	DBTools dbTools = new DBTools(this);
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		//Fetching data from the database about the user, if there is any
		//Puts that data into the form
		ArrayList<HashMap<String, String>> userSessings = dbTools.getUserSettings();
		
		rbGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		this.rbWoman = (RadioButton)findViewById(R.id.rbWoman);
		this.rbMan = (RadioButton)findViewById(R.id.rbMan);
		
		this.txtVWeight = (TextView)findViewById(R.id.txtVWeight);
		this.eTxtWeight = (EditText)findViewById(R.id.eTxtWeight);
		
		this.txtVMedicin = (TextView)findViewById(R.id.txtVExtrawater);
		this.eTxtMedicin = (EditText)findViewById(R.id.eTxtExtraWater);
		
		this.btnSave = (Button)findViewById(R.id.btnSaveSettings);
		
		
		if(userSessings.size() != 0){
			//Toasten funkar som sout
			Toast.makeText(getApplicationContext(), userSessings.get(0).get("isMan"), Toast.LENGTH_LONG).show();
			if(userSessings.get(0).get("isMan").equals("true")){
				rbMan.setChecked(true);
				//rbWoman.setChecked(false);
			}else {
				//rbMan.setChecked(false);
				rbWoman.setChecked(true);
			}
			eTxtWeight.setText(userSessings.get(0).get("weight"));
			eTxtMedicin.setText(userSessings.get(0).get("extraWater"));
			
			this.btnSave.setOnClickListener(new OnClickListener() {
				//And if the save button is clicked, we shall update the database table
				@Override
				public void onClick(View v) {
					//

					// Save all data from the form inputssh
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("id", "1");
					String s;
					if(rbMan.isChecked())
						s = "true";
					else
						s = "false";
					hashMap.put("isMan", s);
					hashMap.put("weight", eTxtWeight.getText().toString());
					hashMap.put("extraWater", eTxtMedicin.getText().toString());
					
					dbTools.updateUser(hashMap);
					Toast.makeText(getApplicationContext(), eTxtMedicin.getText().toString() ,Toast.LENGTH_LONG).show();
				}
			});
			
			
		}else{
			this.btnSave.setOnClickListener(new OnClickListener() {
				//And if the save button is clicked, we shall update the database table
				@Override
				public void onClick(View v) {
					// Save all data from the form inputssh
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("id", "1");
					String s;
					if(rbMan.isChecked())
						s = "true";
					else
						s = "false";
					hashMap.put("isMan", s);
					hashMap.put("weight", eTxtWeight.getText().toString());
					hashMap.put("fever", "0");
					hashMap.put("extraWater", eTxtMedicin.getText().toString());
					
					dbTools.insertUser(hashMap);
					
				}
			});
		}
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
