package com.example.fluidapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class AlarmReminder extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("I are alarm!");
		
		MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.alarm0);
		
		mPlayer.start();
	}
	
		
	
}
