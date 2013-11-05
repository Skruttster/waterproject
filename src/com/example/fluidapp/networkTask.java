package com.example.fluidapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

class networkTask extends AsyncTask<String, Void, HttpResponse> {

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
				String resultString = "";
				BufferedReader rd = new BufferedReader(new InputStreamReader(result.getEntity().getContent()));
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                  sb.append(line + "\n");
                  System.out.println(line);
                }
                resultString = sb.toString();
                JSONObject json = new JSONObject(resultString);
                JSONArray listArray = json.getJSONArray("list");
                JSONObject listObject = listArray.getJSONObject(0);
                JSONObject mainObject= listObject.getJSONObject("main");
                int temperature = mainObject.getInt("temp");
                System.out.println(temperature);
                MainActivity.textview.setText("Temperaturen ute är " + Integer.toString(temperature - 273) + "°C");

                
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println("asdasd");
    }
	
	
	
}