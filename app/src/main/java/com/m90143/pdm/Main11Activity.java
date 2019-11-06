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

        lista.setClickable(true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                String idDados = String.valueOf(carros.get(position).get("id"));
                Intent intent = new Intent(Main11Activity.this, UpdateActivity.class);
                intent.putExtra("idDados", idDados);
                startActivity(intent);
            }

        });

        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
        Integer acesso = settings.getInt("acesso", 1);
        Toast.makeText(this, "Nº de Acessos: " + acesso, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtAno.setText("");
        String query = "SELECT * FROM carro";

        carros = listarCarros(query);
        SimpleAdapter adapter = new SimpleAdapter(this, carros, R.layout.minha_linha3, de, para);
        lista.setAdapter(adapter);
    }

    public void buscarAno(View view){
        String busca = txtAno.getText().toString();
        String query = "";

        if (busca.isEmpty()){
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Deseja voltar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("chave", false);
                        editor.apply();
                        editor.commit();

                        finish();

                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }


    public void addClick(View view) {
        Intent intent = new Intent(Main11Activity.this, AddActivity.class);
        startActivity(intent);
    }
}
