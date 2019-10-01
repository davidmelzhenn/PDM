package com.m90143.pdm;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main7Activity extends AppCompatActivity {

    private ListView listView;
    private List<Map<String,String>> lista;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        listView = findViewById(R.id.listView);
        lista = new ArrayList<>();
        adapter = new MeuAdapter(this, lista, R.layout.minha_linha,
                new String[]{"tv1", "tv2"}, new int[]{R.id.txt1, R.id.txt2});

        listView.setAdapter(adapter);
    }

    public void addClick(View v ){

        String [] arrayNum = {"1","2", "3", "4","5","6","7"};
        String [] arrayDia = {"dom","seg", "ter", "qua", "qui", "sex", "sab"};

        for (int i=0; i<arrayDia.length;i++){
            Map<String,String> map = new HashMap<>();
            map.put("tv1", arrayNum[i]);
            map.put("tv2", arrayDia[i]);
            lista.add(map);
        }

        adapter.notifyDataSetChanged();


    }
}