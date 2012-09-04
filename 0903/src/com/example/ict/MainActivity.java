package com.example.ict;

//import java.util.Random;

import java.util.Random;

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
    
    //‚¨‚Ý‚­‚¶‰æ‘œ‚ðƒNƒŠƒbƒN
	
    
    public void showKuji(View view){
    	ImageView imageView1 = (ImageView)findViewById(R.id.imageView4);
    	    	
    	int kj = 0;    	//kj = R.drawable.kuji1
    	Random rnd = new Random();
    	
    	//switch((int)Math.random() % 5){
    	//switch((int)rnd.nextInt(5)) {
    	switch((int)rnd.nextInt() % 5){
 	   case 1:
 		   kj = R.drawable.kuji1;
		   break;
 	   case 2:
 		   kj = R.drawable.kuji2;
 		   break;
 	   case 3:
 		   kj = R.drawable.kuji2;
 		   break;
 	   case 4:
 		   kj = R.drawable.kuji3;
 		   break;
 	   case 5:
 		   kj = R.drawable.kuji3;
 		   break;
	   default:
		   kj = R.drawable.kuji4;
		   break;
		   /*case 6:
		   //abc="R.drawable.kuji3";
		   //imageView1.setImageResource(R.drawable.kuji3);
		   kj = R.drawable.kuji4;
		   break;*/
		   }
		   imageView1.setImageResource(kj);
    }
 	   
		//imageView1.setImageResource(kj);  //kj = R.drawable.kuji1
    
}
