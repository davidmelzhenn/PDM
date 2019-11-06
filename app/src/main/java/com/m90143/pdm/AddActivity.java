package com.m90143.pdm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText modelo, valor, ano;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        modelo = (EditText) findViewById(R.id.txtModelo);
        valor = (EditText) findViewById(R.id.txtvalor);
        ano = (EditText) findViewById(R.id.txtAno);

        helper = new DatabaseHelper(this);

    }

    public void salvarCarro(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        long resultado = db.insert("carro", null, values);
        if (resultado != -1){
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else{
            Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}
