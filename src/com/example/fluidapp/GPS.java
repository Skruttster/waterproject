package com.example.fluidapp;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class GPS extends Activity implements LocationListener {

public double longitude;
public double latitude;

public double getLongitude()
{
	return this.longitude;
}

public double getLatitude()
{
	return this.latitude;
}
	
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    

    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    
    return true;
}

@Override
public void onLocationChanged(Location location) {
    // TODO Auto-generated method stub

    this.latitude = location.getLatitude();
    this.longitude = location.getLongitude();
}

@Override
public void onProviderDisabled(String provider) {
    // TODO Auto-generated method stub

}

@Override
public void onProviderEnabled(String provider) {
    // TODO Auto-generated method stub

}

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
    // TODO Auto-generated method stub

}

}