package com.m90143.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main10Activity extends AppCompatActivity {

    final String TAG = "APP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    public void ex1Click(View view) {

        Intent intent = new Intent(Main10Activity.this, RestActivity.class);
        startActivity(intent);
    }

    public void ex2Click(View view) {

        Intent intent = new Intent(Main10Activity.this, Rest2Activity.class);
        startActivity(intent);
    }
}
