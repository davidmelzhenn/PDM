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

        String[] clubes = {"Internacional","São Paulo","Palmeiras","Flamengo","Grêmio","Atlético-MG","Cruzeiro","Santos","Fluminense","Corinthians","América-MG","Vitória","Bahia","Atlético-PR","Botafogo","Vasco","Sport","Ceará","Chapecoense","Paraná Clube"};

        int[] pontos = {49,49,46,44,41,38,33,31,31,30,30,29,28,27,26,24,24,24,22,16};

        int[] img = {R.drawable.inter, R.drawable.sao, R.drawable.pal, R.drawable.fla, R.drawable.gre, R.drawable.cam, R.drawable.cru, R.drawable.san, R.drawable.flu, R.drawable.cor, R.drawable.ame, R.drawable.vit, R.drawable.bah, R.drawable.cap, R.drawable.bot, R.drawable.vas, R.drawable.spt, R.drawable.cea, R.drawable.cha, R.drawable.par};


        for (int i=0; i<clubes.length;i++){
            Map<String,String> map = new HashMap<>();
            map.put("tv1", clubes[i]);
            map.put("tv2", String.valueOf(pontos[i]));
            lista.add(map);
        }

        adapter.notifyDataSetChanged();


    }
}