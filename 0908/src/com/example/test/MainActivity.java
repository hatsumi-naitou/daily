package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.*;
import android.widget.*;
// 関数電卓を表示するアクティビティ
public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 縦レイアウトを動的に作成
    final LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    setContentView(layout);
    // 数式の入力欄を作成
    final EditText editText = new EditText(this);
    editText.setText("1+1");
    layout.addView(editText);
    // 計算実行ボタンを作成
    Button button = new Button(this);
    button.setText("計算実行");
    layout.addView(button);
    // 計算実行ボタンにベントをセット
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
            // ブックマークレットのJSとして評価
            wv.loadUrl( "javascript:(function() { " +
              "document.getElementsByTagName('body')[0].innerHTML = (" +
              eval_text + "); })()" );
          }
        });
        // 仮のページを開く
        wv.loadUrl("http://code.google.com/android");
      }
    });
  }
}