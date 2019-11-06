package com.m90143.pdm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main11Activity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText txtAno;
    private ListView lista;
    private List<Map<String,Object>> carros;
    String[] de = {"id", "modelo", "ano", "valor"};
    int[] para = {R.id.txtId, R.id.txtModelo, R.id.txtAno, R.id.txtValor};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        txtAno = findViewById(R.id.txtAno);
        lista = findViewById(R.id.listView);
        helper = new DatabaseHelper(this);

    }

    public void buscarAno(View view){
        String busca = txtAno.getText().toString();
        String query = "";

        if (busca.isEmpty() == false){
            query = "SELECT * FROM carro";
        }else{
            query = "SELECT * FROM carro WHERE ano = " + busca;
        }

        carros = listarCarros(query);
        SimpleAdapter adapter = new SimpleAdapter(this, carros, R.layout.minha_linha3, de, para);
        lista.setAdapter(adapter);
    }

    private List<Map<String, Object>> listarCarros(String query){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        carros = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();
            String id = cursor.getString(0);
            String modelo = cursor.getString(1);
            int ano = cursor.getInt(2);
            double valor = cursor.getDouble(3);
            item.put("id", id);
            item.put("modelo", "Modelo: " + modelo);
            item.put("ano", "Ano: " + ano);
            item.put("valor", String.format("R$ %.2f", valor));
            carros.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return carros;
    }

    public void addClick(View view) {

    }
}
