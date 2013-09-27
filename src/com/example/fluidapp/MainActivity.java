package com.example.fluidapp;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void getLocation()
    {
    	String retJSON = "";
    	String url = "http://api.openweathermap.org/data/2.1/find/city?lat=56.161416&lon=15.583822&cnt=1";
    	
    	    DefaultHttpClient httpClient = new DefaultHttpClient();
    	    HttpGet httpGet = new HttpGet(url);

    	    HttpResponse httpResponse;
			try {
				httpResponse = httpClient.execute(httpGet);
				retJSON = httpResponse.toString();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	System.out.println("derp");
    	
    }
}
