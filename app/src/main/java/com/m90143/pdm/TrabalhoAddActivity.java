package com.m90143.pdm;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class TrabalhoAddActivity extends AppCompatActivity {

    private DatabaseTrabalho database;
    private EditText nome, latitude, longitude;
    private FusedLocationProviderClient client;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho_add);

        nome = (EditText) findViewById(R.id.txtNome);
        latitude = (EditText) findViewById(R.id.txtLatitude);
        longitude = (EditText) findViewById(R.id.txtLongitude);

        database = new DatabaseTrabalho(this);


        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(TrabalhoAddActivity.this, ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED){
            client.getLastLocation().addOnSuccessListener(TrabalhoAddActivity.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        latitude.setText(String.valueOf(location.getLatitude()));
                        longitude.setText(String.valueOf(location.getLongitude()));
                    }
                }
            });
        }

    }

    public void btnSalvar(View view) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome.getText().toString());
        values.put("datahora", new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date()));
        values.put("latitude", latitude.getText().toString());
        values.put("longitude", longitude.getText().toString());

        long resultado = db.insert("recorder", null, values);
        if (resultado != -1){
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else{
            Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        database.close();
        super.onDestroy();
    }

}
