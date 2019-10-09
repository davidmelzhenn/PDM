package com.m90143.pdm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        TextView txtMatricula = findViewById(R.id.txtMatricula);
        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtEstado = findViewById(R.id.txtEstado);
        TextView txtCidade = findViewById(R.id.txtCidade);

        txtMatricula.setText(getIntent().getStringExtra("matricula"));
        txtNome.setText(getIntent().getStringExtra("nome"));
        txtEmail.setText(getIntent().getStringExtra("email"));
        txtEstado.setText(getIntent().getStringExtra("estado"));
        txtCidade.setText(getIntent().getStringExtra("cidade"));


    }

}
