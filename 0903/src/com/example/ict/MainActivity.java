package com.example.ict;

import com.example.ict.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
 class MainActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    //[画像を表示する]ボタンクリック
    
    public void showImage(View view){
    	ImageView imageView1 = (ImageView)findViewById(R.id.imageView4);
    	
    	//String abc = null;
    	int rnd;
    	rnd = (int)Math.random()*10;
    	
 	   switch(rnd){
 	   case 0:
		   //abc="R.drawable.kuji3";
		   imageView1.setImageResource(R.drawable.kuji1);
		   break;
 	   case 1:
 		   //abc="R.drawable.kuji1";
 		   imageView1.setImageResource(R.drawable.kuji1);
 		   break;
 	   case 2:
 		   //abc="R.drawable.kuji2";
 		   imageView1.setImageResource(R.drawable.kuji2);
 		   break;
 	   case 3:
 		   //abc="R.drawable.kuji3";
 		   imageView1.setImageResource(R.drawable.kuji2);
 		   break;
 	   case 4:
 		   //abc="R.drawable.kuji3";
 		   imageView1.setImageResource(R.drawable.kuji2);
 		   break;
 	  case 5:
		   //abc="R.drawable.kuji3";
		   imageView1.setImageResource(R.drawable.kuji3);
		   break;
 	 case 6:
		   //abc="R.drawable.kuji3";
		   imageView1.setImageResource(R.drawable.kuji3);
		   break;
 	 case 7:
		   //abc="R.drawable.kuji3";
		   imageView1.setImageResource(R.drawable.kuji3);
		   break;
 	 case 8:
		   //abc="R.drawable.kuji3";
		   imageView1.setImageResource(R.drawable.kuji3);
		   break;
 	 case 9:
		   //abc="R.drawable.kuji3";
		   imageView1.setImageResource(R.drawable.kuji4);
		   break;
 	   }    	
 	   
		//imageView1.setImageResource(abc);  //abc = R.drawable.kuji1
    }
    
}
