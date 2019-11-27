package com.m90143.pdm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class TrabalhoActivity extends AppCompatActivity {

    private DatabaseTrabalho database;
    private ListView lista;
    private List<Map<String,Object>> dados;
    String[] de = {"id", "nome", "datahora"};
    int[] para = {R.id.txtId, R.id.txtNome, R.id.txtDataHora};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho);

        requestPermission();

        lista = findViewById(R.id.lista);
        database = new DatabaseTrabalho(this);

        lista.setClickable(true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                String idDados = String.valueOf(dados.get(position).get("id"));
                Intent intent = new Intent(TrabalhoActivity.this, TrabalhoUpdateActivity.class);
                intent.putExtra("idDados", idDados);
                startActivity(intent);
            }

        });

        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String query = "SELECT * FROM recorder";

        dados = listarDados(query);
        SimpleAdapter adapter = new SimpleAdapter(this, dados, R.layout.minha_linha4, de, para);
        lista.setAdapter(adapter);
    }

    private List<Map<String, Object>> listarDados(String query){
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        dados = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();
            String id = cursor.getString(0);
            String nome = cursor.getString(1);
            String datahora = cursor.getString(2);
            double valor = cursor.getDouble(3);
            item.put("id", id);
            item.put("nome", "Nome: " + nome);
            item.put("datahora", "Data/Hora: " + datahora);
            dados.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return dados;
    }

    public void btnAdicionar(View view) {
        Intent intent = new Intent(TrabalhoActivity.this, TrabalhoAddActivity.class);
        startActivity(intent);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
