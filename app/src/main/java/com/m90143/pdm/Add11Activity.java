package com.m90143.pdm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Add11Activity extends AppCompatActivity {

    private DatabaseHelper helper ;
    private EditText modelo, valor, ano;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add11);

        modelo = (EditText) findViewById(R.id.modelo);
        ano = (EditText) findViewById(R.id.ano);
        valor = (EditText) findViewById(R.id.valor);

        helper = new DatabaseHelper(this);

    }

    public void salvarCarro (View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", ano.getText().toString());
        values.put("valor", valor.getText().toString());

        long resultado = db.insert("carro", null, values);
        if (resultado != -1){
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limpar();
        }else {
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpar(){
        onDestroy();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}
