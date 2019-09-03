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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void poupancaClick(View view) {
        Intent intent = new Intent(this, PoupancaActivity.class);
        startActivity(intent);
    }

    public void infoClick(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void quizClick(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void restauranteClick(View view) {
        Intent intent = new Intent(this, RestauranteActivity.class);
        startActivity(intent);
    }

}
