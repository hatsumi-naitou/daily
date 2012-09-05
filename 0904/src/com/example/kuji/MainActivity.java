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
    Button button;
    TextView tv;
 
    public void kujiButton(View Button){          //くじボタンのメソッド
        Random ran = new Random();
 
        String text = "test";
        switch((int)ran.nextInt() % 5){
        case 1:
        	text = "大吉";
        	break;
        case 2:
        	text = "中吉";
        	break;
        case 3:
        	text ="小吉";
        	break;
        case 4:
        	text ="大凶";
        	break;
        case 5:
            text ="吉";
            break;
        default :
        	text = "凶";
        	break;
        }
        tv.setText(text);
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        button = (Button)findViewById(R.id.button1);
        tv = (TextView)findViewById(R.id.textView1);

    }
 
  /*  public void onClick(View view){
   * }
   * */
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
        
    }
}