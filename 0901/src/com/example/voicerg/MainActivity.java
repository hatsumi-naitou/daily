package com.example.voicerg;

import java.util.ArrayList;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private static final int REQUEST = 0;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // �����F���̃T���v��
    public void voiceRecognize(View v){
    	try{
    		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "�����F���e�X�g");
    		startActivityForResult(intent, REQUEST);
    	} catch(ActivityNotFoundException e){
    		Toast.makeText(MainActivity.this, "�A�N�e�B�r�e�B���݂���܂���ł����B", Toast.LENGTH_LONG).show();
    	}
    }
    
    // �A�N�e�B�r�e�B�I��
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	if(requestCode == REQUEST && resultCode == RESULT_OK){
    		String voiceString = "";
    		ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
    		for (int i = 0; i< results.size(); i++) {
    			voiceString += results.get(i);
    		}
    		Toast.makeText(this, voiceString, Toast.LENGTH_LONG).show();
    	}
    		super.onActivityResult(requestCode, resultCode, data);
    }
}
