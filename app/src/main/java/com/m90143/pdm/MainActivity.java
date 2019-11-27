package com.m90143.pdm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String TAG = "APP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    public void aula2Click(View view) {

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    public void aula3Click(View view) {
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        startActivity(intent);
    }

    public void aula4Click(View view) {
        Intent intent = new Intent(MainActivity.this, Main4Activity.class);
        startActivity(intent);
    }

    public void aula6Click(View view) {
        Intent intent = new Intent(MainActivity.this, Main6Activity.class);
        startActivity(intent);
    }

    public void aula7Click(View view) {
        Intent intent = new Intent(MainActivity.this, Main7Activity.class);
        startActivity(intent);
    }

    public void aula8Click(View view) {
        Intent intent = new Intent(MainActivity.this, Main8Activity.class);
        startActivity(intent);
    }

    public void desafioClick(View view) {
        Intent intent = new Intent(MainActivity.this, DesafioActivity.class);
        startActivity(intent);
    }

    public void aula10Click(View view) {
        Intent intent = new Intent(MainActivity.this, Main10Activity.class);
        startActivity(intent);
    }

    public void aula11Click(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void trabalhoClick(View view) {
        Intent intent = new Intent(MainActivity.this, TrabalhoActivity.class);
        startActivity(intent);
    }
}
