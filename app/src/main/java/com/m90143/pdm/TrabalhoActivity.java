package com.m90143.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class TrabalhoActivity extends AppCompatActivity{

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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                final String idDados = String.valueOf(dados.get(position).get("id"));
                final String nome = String.valueOf(dados.get(position).get("nome"));
                final String latitude = String.valueOf(dados.get(position).get("latitude"));
                final String longitude = String.valueOf(dados.get(position).get("longitude"));

                PopupMenu popup = new PopupMenu(view.getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());

                //Handle Menu Click
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        {
                            Intent intent;
                            switch (item.getItemId()){
                                case R.id.mapa:

                                    intent = new Intent(TrabalhoActivity.this, MapsActivity.class);
                                    intent.putExtra("idDados", idDados);
                                    intent.putExtra("nome", nome);
                                    intent.putExtra("latitude", latitude);
                                    intent.putExtra("longitude", longitude);
                                    startActivity(intent);
                                    return true;

                                case R.id.player:
                                    intent = new Intent(TrabalhoActivity.this, TrabalhoPlayerActivity.class);
                                    intent.putExtra("idDados", idDados);
                                    startActivity(intent);
                                    return true;

                                case R.id.excluir:
                                    excluir(idDados);
                                    return true;
                            }
                        }
                        return false;
                    }
                });
                popup.show();
            }

        });

        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
    }

    public void excluir(String id){
        SQLiteDatabase db = database.getWritableDatabase();
        String where[] = new String[] {id};

        long resultado = db.delete("recorder", "id = ?", where);
        if (resultado != -1){
            Toast.makeText(this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }

        dados = listarDados("SELECT * FROM recorder");
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
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String datahora = cursor.getString(cursor.getColumnIndex("datahora"));
            String latitude = cursor.getString(cursor.getColumnIndex("latitude"));
            String longitude = cursor.getString(cursor.getColumnIndex("longitude"));
            item.put("id", id);
            item.put("nome", "Nome: " + nome);
            item.put("datahora", "Data/Hora: " + datahora);
            item.put("latitude", latitude);
            item.put("longitude", longitude);
            dados.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return dados;
    }

    @Override
    protected void onStart() {
        super.onStart();

        dados = listarDados("SELECT * FROM recorder");
        SimpleAdapter adapter = new SimpleAdapter(this, dados, R.layout.minha_linha4, de, para);
        lista.setAdapter(adapter);
    }

    public void btnAdicionar(View view) {
        Intent intent = new Intent(TrabalhoActivity.this, TrabalhoAddActivity.class);
        startActivity(intent);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

}
