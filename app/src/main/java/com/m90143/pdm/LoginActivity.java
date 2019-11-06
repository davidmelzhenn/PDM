package com.m90143.pdm;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario, senha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.txtUsuario);
        senha = (EditText) findViewById(R.id.txtSenha);

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
        boolean chave = settings.getBoolean("chave", false);
        Integer acesso = settings.getInt("acesso", 0);

        if (chave == true){

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("acesso", acesso + 1);
            editor.apply();
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, Main11Activity.class);
            startActivity(intent);
        }
    }

    public void Acessar(View view) {
        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("usuario", usuario.getText().toString());
        editor.putString("senha", senha.getText().toString());
        editor.putBoolean("chave", true);
        editor.putInt("acesso", 1);
        editor.apply();
        editor.commit();

        Intent intent = new Intent(LoginActivity.this, Main11Activity.class);
        startActivity(intent);

    }
}
