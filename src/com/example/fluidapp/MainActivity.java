package com.example.fluidapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {
//TOP LEL TWILIGHTSSON
	
	public static TextView textview; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.txtVMessages);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void getLocation(View view)
    {
    	
    	System.out.println("derp");
    	
    	String url = "http://api.openweathermap.org/data/2.1/find/city?lat=56.161416&lon=15.583822&cnt=1";
    	
    	new networkTask().execute(url);
    	
    }
    
    public void setMainMessage(String message)
    {
    	TextView t = (TextView)findViewById(R.id.txtVMessages);
    	t.setText(message);
    }
    
}


