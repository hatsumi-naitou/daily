package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 一覧表示アクティビティ
 */
public class MainActivity extends Activity {
// 一覧表示用ListView
private ListView listView = null;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 自動生成されたR.javaの定数を指定してXMLからレイアウトを生成
        setContentView(R.layout.activity_main);

        // XMLで定義したandroid:idの値を指定してListViewを取得します。
        listView = (ListView) findViewById(R.id.list);

        // ListViewに表示する要素を保持するアダプタを生成します。
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1);
        // 要素を追加
        arrayAdapter.add("新規追加");
        // アダプタを設定
        listView.setAdapter(arrayAdapter);
}
}
