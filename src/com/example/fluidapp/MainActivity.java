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
	
	private TextView txtVM;
	
	
	private Button btnFever;
	private Button btnSendRequest;
	private Button btnClear;
	private Button btnSettings;
	private Button btnInfo;
	private final Context context = this;
	
	private DBTools dbTools =  new DBTools(this);
	private String[] arr = {"38", "39", "40", "41", "42", "Ã¥ngra, ingen feber"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.txtVMessages);
        txtVM = (TextView)findViewById(R.id.textViewAmount);
        GPSLocation = new GPSTracker(this);
        if(savedInstanceState == null){
        	messages = "Inga nya meddelanden";
        }else{
        	messages = savedInstanceState.getString(NEW_MESSAGES);
        }
        
        
        //Aktivera alarm
        alarmManager();
        
        //txtVMessages = (TextView)findViewById(R.id.txtVMessages);
        
        
    	btnFever = (Button)findViewById(R.id.btnFever);
    	btnSendRequest = (Button)findViewById(R.id.btn);
    	//btnClear = (Button)findViewById(R.id.btnClear);
    	btnSettings = (Button)findViewById(R.id.btnSettings);
    	btnInfo = (Button)findViewById(R.id.btnInfo);
    	


//    	Knapptryckningar
    	
    	//This button sparks a dialogue allowing the user to let the system know that he or she has a fever
    	//The amount of fever changes the amount of water the user should drink during that day
    	//A high fever will trigger a toast telling the user to seek out a doctor
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
						updateDrinkingAmount();
					}
				});
				
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				
			}
		});
    	//Clicking this button will send you to the settings page
    	btnSettings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nextScreen = new Intent(getApplicationContext(), SettingsActivity.class);				
				startActivity(nextScreen);
				 
			}
		});
    	
    	
    	//Clicking this button will redirect us to another page. On that page We have stored some more information
    	btnInfo.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Intent nextScreen = new Intent(getApplicationContext(), AboutActivity.class);
    			startActivity(nextScreen);
    			
    		}
    	});
    	
//    	btnClear.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				updateDrinkingAmount();
//			}
//		});
    	
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
    	updateDrinkingAmount();
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
    	AlarmManager alarmMgr1 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	AlarmManager alarmMgr2 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

    	//Create pending intent & register it to your alarm notifier class
    	Intent intent0 = new Intent(MainActivity.this, AlarmReminder.class);
    	PendingIntent pendingIntent0 = PendingIntent.getActivity(MainActivity.this, 0, intent0, PendingIntent.FLAG_CANCEL_CURRENT);
    	
    	Intent intent1 = new Intent(MainActivity.this, AlarmReminder.class);
    	PendingIntent pendingIntent1 = PendingIntent.getActivity(MainActivity.this, 1, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
    	
    	Intent intent2 = new Intent(MainActivity.this, AlarmReminder.class);
    	PendingIntent pendingIntent2 = PendingIntent.getActivity(MainActivity.this, 2, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

    	Calendar timeOff11 = Calendar.getInstance();
    	timeOff11.set(Calendar.HOUR_OF_DAY, 11);
    	timeOff11.set(Calendar.MINUTE, 00);
    	timeOff11.set(Calendar.SECOND, 30);
    	
    	Calendar timeOff15 = Calendar.getInstance();
    	timeOff15.set(Calendar.HOUR_OF_DAY, 15);
    	timeOff15.set(Calendar.MINUTE, 00);
    	timeOff15.set(Calendar.SECOND, 30);
    	
    	Calendar timeOff18 = Calendar.getInstance();
    	timeOff18.set(Calendar.HOUR_OF_DAY, 18);
    	timeOff18.set(Calendar.MINUTE, 00);
    	timeOff18.set(Calendar.SECOND, 30);
        
        alarmMgr0.set(AlarmManager.RTC_WAKEUP, timeOff11.getTimeInMillis(), pendingIntent0);
        alarmMgr1.set(AlarmManager.RTC_WAKEUP, timeOff15.getTimeInMillis(), pendingIntent1);
        alarmMgr2.set(AlarmManager.RTC_WAKEUP, timeOff18.getTimeInMillis(), pendingIntent2);
        
        

    	
    }
    
    //Metoden uppdaterar textfältet på startsidan med hur mycket man bör dricka den dagen
    //metoden kallas när man trycker uppdatera och när man trycker på feberknappen
    private void updateDrinkingAmount(){
    	ArrayList<HashMap<String, String>> settings = dbTools.getUserSettings();
		
		if(settings.size() > 0){
			int weight = Integer.parseInt(settings.get(0).get("weight"));
			int fever = Integer.parseInt(settings.get(0).get("fever"));
//				if(settings.get(0).get("isMan").equals("true")){
//					//int result = ((weight * 30) + ((weight * 30)*(10/fever)));
//					//msg = "Kom ihÃ¥g att du bÃ¶r dricka "+ Integer.toString(weight) + "ml";
//					//setMainMessage(msg);
//				}else{
//					//int result = ((weight * 30) + ((weight * 30)*(10/fever)));
//					//msg = "Kom ihÃ¥g att du bÃ¶r dricka "+ Integer.toString(weight) + "ml idag";
//					//setMainMessage(msg);
//					//msg = "Kom ihÃ¥g att du bÃ¶r dricka "+ ((weight * 30) + ((weight * 30)*(10/fever)));
//				}
			if(fever > 0){
				txtVM.setText("Kom ihång att dricka ungefär " + ((weight * 30) + (((weight*30)*fever)/10)) + "ml idag");
			}else{
				txtVM.setText("Kom ihång att dricka ungefär " + (weight * 30) + "ml idag");
			}
		}
		else{
			txtVM.setText("");
		}
    }
}


