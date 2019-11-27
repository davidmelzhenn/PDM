package com.m90143.pdm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update11Activity extends AppCompatActivity {

    private DatabaseHelper helper ;
    private EditText modelo, valor, ano;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update11);

        modelo = (EditText) findViewById(R.id.modelo);
        ano = (EditText) findViewById(R.id.ano);
        valor = (EditText) findViewById(R.id.valor);

        helper = new DatabaseHelper(this);

    }

    public void atualizarCarro (View view){

    }

    public void excluirCarro (View view){

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
