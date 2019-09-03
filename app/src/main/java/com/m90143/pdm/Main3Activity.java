package com.m90143.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {

    private EditText txtValorInicial, txtAplicacaoMensal, txtTempoAplicacao, txtTaxa;
    private TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtValorInicial = findViewById(R.id.txtValorInicial);
        txtAplicacaoMensal = findViewById(R.id.txtAplicacaoMensal);
        txtTempoAplicacao = findViewById(R.id.txtTempoAplicacao);
        txtTaxa = findViewById(R.id.txtTaxa);
        lblResultado = findViewById(R.id.lblResultado);

    }

    public void calcClick(View view) {
        Double mValorInicial = Double.parseDouble(txtValorInicial.getText().toString());
        Double mAplicacaoMensal = Double.parseDouble(txtAplicacaoMensal.getText().toString());
        Integer mTempoAplicacao = Integer.parseInt(txtTempoAplicacao.getText().toString());
        Double mTaxa = Double.parseDouble(txtTaxa.getText().toString());

        if (mValorInicial == 0) {
            Toast.makeText(this, "Valor Inicial inválido. Verifique!", Toast.LENGTH_SHORT).show();
            txtValorInicial.requestFocus();
        }
        if (mAplicacaoMensal == 0) {
            Toast.makeText(this, "Aplicação Mensal inválida. Verifique!", Toast.LENGTH_SHORT).show();
            txtAplicacaoMensal.requestFocus();
        }
        if (mTempoAplicacao == 0) {
            Toast.makeText(this, "Tempo Aplicação inválido. Verifique!", Toast.LENGTH_SHORT).show();
            txtTempoAplicacao.requestFocus();
        }
        if (mTaxa == 0) {
            Toast.makeText(this, "Taxa inválida. Verifique!", Toast.LENGTH_SHORT).show();
            txtTaxa.requestFocus();
        }

        Double mResultado = (mAplicacaoMensal * (Math.pow(1 + mTaxa, mTempoAplicacao) - 1)) / mTaxa;
        mResultado += mValorInicial;
        lblResultado.setText(String.format("Resultado R$: %.2f", mResultado));

    }
}
