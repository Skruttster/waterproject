package com.example.fluidapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
//TOP LEL TWILIGHTSSON
	
	public static TextView textview;
	public GPSTracker GPSLocation;
	
	
private static final String NEW_MESSAGES = "NEW_MESSAGES";
	
	private String messages;
	
	private TextView txtVMessages;
	
	private Button btnFever;
	private Button btnSendRequest;
	private Button btnClear;
	private Button btnSettings;
	private Button btnInfo;
	private final Context context = this;
	
	private DBTools dbTools =  new DBTools(this);
	private String[] arr = {"38", "39", "40", "41", "42", "ångra, ingen feber"};
	
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
        
        
    	btnFever = (Button)findViewById(R.id.btnFever);
    	btnSendRequest = (Button)findViewById(R.id.btn);
    	btnClear = (Button)findViewById(R.id.btnClear);
    	btnSettings = (Button)findViewById(R.id.btnSettings);
    	btnInfo = (Button)findViewById(R.id.btnInfo);
    	


//    	Knapptryckningar
    	btnFever.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Ange om du har feber");
				builder.setItems(arr, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						HashMap<String, String> hashMap = new HashMap<String, String>();
						hashMap.put("id", "1");
						switch (which) {
						case 0:
							
							hashMap.put("fever", "1");
							dbTools.updateUserFever(hashMap);
							Toast.makeText(getApplicationContext(), "Din feber är noterad", Toast.LENGTH_LONG).show();
							break;
						case 1:
							hashMap.put("fever", "2");
							dbTools.updateUserFever(hashMap);
							Toast.makeText(getApplicationContext(), "Din feber är noterad.", Toast.LENGTH_LONG).show();
							break;
						case 2:
							hashMap.put("fever", "3");
							dbTools.updateUserFever(hashMap);
							Toast.makeText(getApplicationContext(), "Hög feber, uppsök läkare", Toast.LENGTH_LONG).show();
							break;
						case 3:
							hashMap.put("fever", "4");
							dbTools.updateUserFever(hashMap);
							Toast.makeText(getApplicationContext(), "Hög feber, uppsök läkare", Toast.LENGTH_LONG).show();
							break;
						case 4:
							hashMap.put("fever", "5");
							dbTools.updateUserFever(hashMap);
							Toast.makeText(getApplicationContext(), "Hög feber, uppsök läkare", Toast.LENGTH_LONG).show();
							break;

						default:
							hashMap.put("fever", "0");
							dbTools.updateUserFever(hashMap);
							Toast.makeText(getApplicationContext(), "Ingen feber registerad", Toast.LENGTH_LONG).show();
							break;
						}
					}
				});
				
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				
			}
		});
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
				ArrayList<HashMap<String, String>> settings = dbTools.getUserSettings();
				String msg = "";
				if(settings.size() > 0){
					int weight = Integer.parseInt(settings.get(0).get("weight"));
					int fever = Integer.parseInt(settings.get(0).get("fever"));
	//				if(settings.get(0).get("isMan").equals("true")){
	//					//int result = ((weight * 30) + ((weight * 30)*(10/fever)));
	//					//msg = "Kom ihåg att du bör dricka "+ Integer.toString(weight) + "ml";
	//					//setMainMessage(msg);
	//				}else{
	//					//int result = ((weight * 30) + ((weight * 30)*(10/fever)));
	//					//msg = "Kom ihåg att du bör dricka "+ Integer.toString(weight) + "ml idag";
	//					//setMainMessage(msg);
	//					//msg = "Kom ihåg att du bör dricka "+ ((weight * 30) + ((weight * 30)*(10/fever)));
	//				}
					if(fever > 0){
						textview.setText("Kom ihång att dricka " + ((weight * 30) + (((weight*30)*fever)/10)) + "ml idag");
					}else{
						textview.setText("Kom ihång att dricka " + (weight * 30) + "ml idag");
					}
				}
				else{
					textview.setText("");
				}
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
    	alarmManager();
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
    	Intent intent0 = new Intent(MainActivity.this, AlarmReminder.class);
    	PendingIntent pendingIntent0 = PendingIntent.getActivity(MainActivity.this, 0, intent0, PendingIntent.FLAG_CANCEL_CURRENT);

    	//set timer you want alarm to work (here I have set it to 7.20pm)
    	//Calendar timeOff9 = Calendar.getInstance();
    	//timeOff9.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
    	//timeOff9.set(Calendar.MINUTE, Calendar.MINUTE);
    	//timeOff9.set(Calendar.SECOND, Calendar.SECOND);
    	
    	Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        
        alarmMgr0.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent0);
        
        
    	//set that timer as a RTC Wakeup to alarm manager object
    	//alarmMgr0.set(AlarmManager.RTC_WAKEUP, timeOff9.getTimeInMillis(), pendingIntent0);
    	
    }
    
    
    
}


