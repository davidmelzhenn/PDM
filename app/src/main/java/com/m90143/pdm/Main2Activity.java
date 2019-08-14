package com.m90143.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    final String TAG = "CICLO";
    private EditText txtTempC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtTempC = findViewById(R.id.txtTempC);

        //A activity está sendo criada
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        //A activity está prestes a se tornar visível
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        //A Activity está visível
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
        //A activity está voltando a receber o foco, depois de estar pausada
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        //A actibity está recevendo o foco. Esta activity
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        //A activity não está ms visível mas permanece em memória
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        //A activity está prestes a ser destruída (removida da memória).
    }

    public void calcClick(View view) {
        String celcius = txtTempC.getText().toString();
        if (!celcius.isEmpty()){
            //Toast.makeText(this, celcius, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, TempActivity.class);
            intent.putExtra("Celcius", celcius);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Temperatura inválida. Verifique!", Toast.LENGTH_LONG).show();
        }

    }
}
