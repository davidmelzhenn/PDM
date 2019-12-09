package com.m90143.pdm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private DatabaseTrabalho database;
    GoogleMap mGoogleMap;
    String idDados, nome, latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        database = new DatabaseTrabalho(this);

        Bundle extras = getIntent().getExtras();
        idDados = extras.getString("idDados");
        //nome = extras.getString("nome");
        latitude = extras.getString("latitude");
        longitude = extras.getString("longitude");
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        mGoogleMap = gMap;
        Marker mMarker = null;
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recorder", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String datahora = cursor.getString(cursor.getColumnIndex("datahora"));
            String lat = cursor.getString(cursor.getColumnIndex("latitude"));
            String lon = cursor.getString(cursor.getColumnIndex("longitude"));


            if (!lat.isEmpty() && !lon.isEmpty()){
                MarkerOptions options = new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                        .title(id)
                        .snippet("Nome: " + nome + " | Data-Hora: " + datahora);

                if (idDados.equals(id)){
                    mGoogleMap.addMarker(options).showInfoWindow();
                } else {
                    mGoogleMap.addMarker(options);
                }
            }
            cursor.moveToNext();
        }

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)), 15.0f));

        gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, TrabalhoPlayerActivity.class);
                intent.putExtra("idDados", String.valueOf(marker.getTitle()));
                startActivity(intent);
            }
        });

    }

}