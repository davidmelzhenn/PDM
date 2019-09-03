package com.m90143.pdm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RestauranteActivity extends AppCompatActivity {

    EditText txtConsumoTotal, txtCouvert, txtDividirConta, txtTaxaServico, txtContaTotal, txtValorPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ico_balaio_lenha);
        getSupportActionBar().setTitle("BalaioDeLenha");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        txtConsumoTotal = findViewById(R.id.txtConsumoTotal);
        txtCouvert = findViewById(R.id.txtCouvert);
        txtDividirConta = findViewById(R.id.txtDividirConta);

        txtTaxaServico = findViewById(R.id.txtTaxaServico);
        txtContaTotal = findViewById(R.id.txtContaTotal);
        txtValorPessoa = findViewById(R.id.txtValorPessoa);

    }

    public void calcularClick(View view) {

        Double mConsumoTotal = Double.parseDouble(txtConsumoTotal.getText().toString());
        Double mCouvert = Double.parseDouble(txtCouvert.getText().toString());
        Integer mDividirConta = Integer.parseInt(txtDividirConta.getText().toString());


        if (mDividirConta <= 0) {
            Toast.makeText(this, "Valor dividir conta inválido. Verifique!", Toast.LENGTH_SHORT).show();
            txtDividirConta.requestFocus();
        }

        Double mResult = (mConsumoTotal + mCouvert) / mDividirConta;

        Double mTaxaServico = (10 * mResult) / 100;
        txtTaxaServico.setText(String.format("%.2f", mTaxaServico));

        Double mTotal = mResult + mTaxaServico;
        txtContaTotal.setText(String.format("%.2f", mTotal));

        Double mValorPessoa = mTotal / mDividirConta;
        txtValorPessoa.setText(String.format("%.2f", mValorPessoa));

    }
}
