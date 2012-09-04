package com.example.ict;

import java.net.URL;

import com.example.ict.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    //[画像を表示する]ボタンクリック
    
    public void showImage(View view){
    	ImageView imageView1 = (ImageView)findViewById(R.id.imageView4);
    	imageView1.setImageResource(R.drawable.kuji1);
    }
    
}
