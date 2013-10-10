package com.example.fluidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
//TOP LEL TWILIGHTSSON
	
	public static TextView textview;
	public GPS GPSLocation;
	
	
private static final String NEW_MESSAGES = "NEW_MESSAGES";
	
	private String messages;
	
	private TextView txtVMessages;
	
	private Button btnSendRequest;
	private Button btnClear;
	private Button btnSettings;
	private Button btnInfo;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.txtVMessages);
        GPSLocation = new GPS();
        if(savedInstanceState == null){
        	messages = "Inga nya meddelanden";
        }else{
        	messages = savedInstanceState.getString(NEW_MESSAGES);
        }
        
        //txtVMessages = (TextView)findViewById(R.id.txtVMessages);
    	
    	btnSendRequest = (Button)findViewById(R.id.btn);
    	btnClear = (Button)findViewById(R.id.btnClear);
    	btnSettings = (Button)findViewById(R.id.btnSettings);
    	btnInfo = (Button)findViewById(R.id.btnInfo);

//    	Knapptryckningar
    	btnSettings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nextScreen = new Intent(getApplicationContext(), SettingsActivity.class);
				
				startActivity(nextScreen);
				 
			}
		});
    	
    	btnInfo.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Intent nextScreen = new Intent(getApplicationContext(), AboutActivity.class);
    			
    			startActivity(nextScreen);
    			
    		}
    	});
    	
    	
    	
    	btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtVMessages.setText("Inga nya meddelande nu häller");
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void getLocation(View view)
    {
    	
    	System.out.println(GPSLocation.getLatitude());
    	System.out.println(GPSLocation.getLongitude());
    	
    	String url = "http://api.openweathermap.org/data/2.1/find/city?lat=" + Double.toString(GPSLocation.getLatitude()) + "&lon=" + Double.toString(GPSLocation.getLongitude()) + "&cnt=1";
    	
    	new networkTask().execute(url);
    	
    }
    
    public void setMainMessage(String message)
    {
    	TextView t = (TextView)findViewById(R.id.txtVMessages);
    	t.setText(message);
    }
    
}


