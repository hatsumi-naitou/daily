package com.example.kuji;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.lang.String;
 
public class MainActivity extends Activity {
    Button myButton;
    TextView myTextView;
 
    //methods
    public void changeText(View theButton){
        Random rand = new Random();
 
        String text = "test";
        switch((int)rand.nextInt() % 5){
        case 1:
        text = "��g";
        break;
        case 2:
        text = "���g";
        break;
        case 3:
        text ="���g";
        break;
        case 4:
            text ="�勥";
            break;
        case 5:
            text ="�G�g";
            break;
        default :
        text = "��";
        break;
        }
        myTextView.setText(text);
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        myButton = (Button)findViewById(R.id.button1);
        myTextView = (TextView)findViewById(R.id.textView1);
       // myButton.setOnClickListener(this);
    }
 
    public void onClick(View view){
        //changeText();
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}