package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.*;
import android.widget.*;
// �֐��d���\������A�N�e�B�r�e�B
public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // �c���C�A�E�g�𓮓I�ɍ쐬
    final LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);
    // �����̓��͗����쐬
    final EditText editText = new EditText(this);
    editText.setText("1+1");
    layout.addView(editText);
    // �v�Z���s�{�^�����쐬
    Button button = new Button(this);
    button.setText("�v�Z���s");
    layout.addView(button);
    // �v�Z���s�{�^���Ƀx���g���Z�b�g
    final Activity this_activity = this;
    button.setOnClickListener(new OnClickListener(){
      public void onClick(View v) {
        final String eval_text = editText.getText().toString();
        final WebView wv = new WebView(this_activity);
        this_activity.setContentView(wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
          @Override
          public void onPageFinished(WebView view, String url)
          {
            // �u�b�N�}�[�N���b�g��JS�Ƃ��ĕ]��
            wv.loadUrl( "javascript:(function() { " +
              "document.getElementsByTagName('body')[0].innerHTML = (" +
              eval_text + "); })()" );
          }
        });
        // ���̃y�[�W���J��
        wv.loadUrl("http://code.google.com/android");
      }
    });
  }
}