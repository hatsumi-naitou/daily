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
 
    public void kujiButton(View Button){          //�����{�^���̃��\�b�h
        Random ran = new Random();
 
        String text = "test";
        switch((int)ran.nextInt() % 5){
        case 1:
        	text = "�w�� �g�x�@���ǂ��P������";
        	break;
        case 2:
        	text = "�w�� �g�x�@�}�C�y�[�X�ɂ������I";
        	break;
        case 3:
        	text ="�w���@�g�x�@�����ʂ�̂P����";
        	break;
        case 4:
        	text ="�w��@���x�@�`���̂ɋC�����ā`";
        	break;
        case 5:
            text ="�w �g �x�@������Ɨǂ������邩��";
            break;
        default :
        	text = "�w �� �x�@�����͍������ǂ��͂��I";
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