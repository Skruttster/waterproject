package com.example.fluidapp;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
	public GPSTracker GPSLocation;
	
	
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
        GPSLocation = new GPSTracker(this);
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
    	
    	alarmManager();

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
    
    private void alarmManager()
    {
    	//Create alarm manager
    	AlarmManager alarmMgr0 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

    	//Create pending intent & register it to your alarm notifier class
    	Intent intent0 = new Intent(this, AlarmReminder.class);
    	PendingIntent pendingIntent0 = PendingIntent.getBroadcast(this, 0, intent0, 0);

    	//set timer you want alarm to work (here I have set it to 7.20pm)
    	//Calendar timeOff9 = Calendar.getInstance();
    	//timeOff9.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
    	//timeOff9.set(Calendar.MINUTE, Calendar.MINUTE);
    	//timeOff9.set(Calendar.SECOND, Calendar.SECOND);
    	
    	Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        
        alarmMgr0.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10*1000, pendingIntent0);
        
    	//set that timer as a RTC Wakeup to alarm manager object
    	//alarmMgr0.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pendingIntent0);
    	
    }
    
}


