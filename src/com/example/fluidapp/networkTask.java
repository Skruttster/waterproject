package com.example.fluidapp;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;


public class networkTask extends AsyncTask<String, Void, HttpResponse> {
    @Override
    protected HttpResponse doInBackground(String... params) {
        String link = params[0];
        HttpGet request = new HttpGet(link);
        AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        try {
            return client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
        client.close();
    }
    }

    @Override
    protected void onPostExecute(HttpResponse result) {
        //Do something with result
        if (result != null)
			try {
				System.out.println(result.getEntity().getContent());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("asdasd");
    }
}