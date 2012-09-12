package com.example.test;

import android.app.Activity;
import android.os.Bundle;

/**
 * 登録アクティビティ
 */
public class RegistActivity extends Activity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                // 自動生成されたR.javaの定数を指定してXMLからレイアウトを生成
                setContentView(R.layout.regist);
        }
}
