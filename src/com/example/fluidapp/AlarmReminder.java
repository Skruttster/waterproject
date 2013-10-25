package com.example.fluidapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;

public class AlarmReminder extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("I are alarm!");
		
		MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.alarm0);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Drick");
		builder.setNeutralButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mPlayer.start();
	}
	
		
	
}
