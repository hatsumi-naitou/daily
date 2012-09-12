package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * �ꗗ�\���A�N�e�B�r�e�B
 */
public class MainActivity extends Activity {
// �ꗗ�\���pListView
private ListView listView = null;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �����������ꂽR.java�̒萔���w�肵��XML���烌�C�A�E�g�𐶐�
        setContentView(R.layout.activity_main);

        // XML�Œ�`����android:id�̒l���w�肵��ListView���擾���܂��B
        listView = (ListView) findViewById(R.id.list);

        // ListView�ɕ\������v�f��ێ�����A�_�v�^�𐶐����܂��B
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1);
        // �v�f��ǉ�
        arrayAdapter.add("�V�K�ǉ�");
        // �A�_�v�^��ݒ�
        listView.setAdapter(arrayAdapter);
}
}
