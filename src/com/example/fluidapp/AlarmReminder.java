package com.example.fluidapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmReminder extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
			setContentView(R.layout.alarm);
			
			final MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.alarm0);
			mPlayer.start();
			
			Button stopAlarm = (Button) findViewById(R.id.btnStopAlarm);
			stopAlarm.setOnClickListener(new Button.OnClickListener() {  
		        public void onClick(View v)
		            {
		                finish();
		                mPlayer.stop();
		            }
		         });
			
			
	}
	
		
	
}
