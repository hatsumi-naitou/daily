package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


	public void showHelloText(View view){
    	Toast myToast = Toast.makeText(this, "�e�X�g�e�X�g�B�������܃e�X�g��", Toast.LENGTH_LONG);
    	myToast.setGravity(Gravity.CENTER, 0, 0);
    	myToast.show();
    	
    }
    
}
