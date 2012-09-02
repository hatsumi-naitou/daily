package com.example.ImageViewer;

import java.net.URL;
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
    	ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
    	imageView1.setImageResource(R.drawable.sample);
    }
    
    //[画像を表示する2]ボタンクリック
    public void showImage2(View view){
    	String url = "http://www.ikachi.org/graphic/military/sky/m001a.jpg";
    	try{
    		Drawable sample = Drawable.createFromStream(new URL(url).openStream(), null);
    		ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
    		imageView1.setImageDrawable(sample);
    	} catch(Exception e) {
    		Toast myToast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
    		myToast.setGravity(Gravity.CENTER, 0, 0);
    		myToast.show();
    	}
    }

    
}
