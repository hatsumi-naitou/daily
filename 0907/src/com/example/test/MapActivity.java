package com.example.test;

import android.os.Bundle;


public class MapActivity extends com.google.android.maps.MapActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 
		setContentView(R.layout.map);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}