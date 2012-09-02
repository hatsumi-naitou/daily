package com.example.mylocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MapController;

public class MainActivity extends MapActivity implements LocationListener {
	private final static String API_KEY = "01XEkyObLGDRFCz3xqE81DFhzP6eCsebQe1XKjg";
	
	private MapView mapView;
	private MapController mapCtrl;
	private LocationManager lm;
	
	// アクティビティ作成
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        mapView = new MapView(this, API_KEY);
        setContentView(mapView);
        mapCtrl = mapView.getController();
        mapCtrl.setZoom(16);
    }
    
    // アクティビティ表示
    @Override
    public void onStart(){
    	super.onStart();
    	lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }
    
    // アクティビティ非表示
    @Override
    public void onStop(){
    	super.onStop();
    	lm.removeUpdates(this);
    }
    
    @Override
    public void onLocationChanged(Location location){
    	GeoPoint pos = new GeoPoint(
    			(int)(location.getLatitude() * 1E6),
    			(int)(location.getLongitude() * 1E6));
    		mapCtrl.setCenter(pos);
    }
    
    @Override
    public void onProviderDisabled(String provider){
    	
    }

    @Override
    public void onProviderEnabled(String provider){
    
    }
    
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){
    	
    }
    
    
    @Override
    public boolean isRouteDisplayed(){
        return false;
    }

    
}
