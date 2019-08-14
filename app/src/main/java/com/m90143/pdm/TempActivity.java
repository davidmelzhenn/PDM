package com.m90143.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    private TextView txtTempF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        txtTempF = findViewById(R.id.txtTempF);

        String celcius = getIntent().getStringExtra("Celcius");

        double fahreinheit = (Double.parseDouble(celcius) * 9 / 5) + 32;

        txtTempF.setText(fahreinheit + "°F");

    }
}
