package com.example.fluidapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {

	private Button btnAboutApp;
	private Button btnCreatedBy;
	private Button btnMoreInfo;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// Show the Up button in the action bar.
		setupActionBar();
		btnAboutApp = (Button)findViewById(R.id.btn);
		btnCreatedBy = (Button)findViewById(R.id.btnCreatedBy);
		btnMoreInfo = (Button)findViewById(R.id.bthMoreInfo);
		
		
		btnAboutApp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Om applikationen");
				builder.setMessage("Tanken är att appen ska kunna vara ett stöd i vardagen för de äldre.\n" +
						"Appen ger påminnelser om att det kan vara dags att ta ett glasvatten när det är extra varm ute, eller när användaren rört sig mycket");
				builder.setNeutralButton("Tillbaka", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});
		
		btnCreatedBy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Om applikationen");
				builder.setMessage("Skapad av de fyra BTH-studenterna: Marcus Rehn, Emil Sunesson, Daniel Bergström och Max Känngård.");
				builder.setNeutralButton("Tillbaka", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});
		btnMoreInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.karlskrona.se/sv/Familj--omsorg/Aldre/Maltider/Energibehov/"));
				startActivity(intent);
			}
		});
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
		getMenuInflater().inflate(R.menu.about, menu);
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
