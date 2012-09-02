package com.example.myamera;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(new MyCameraView(this));
		
	}
    
}
