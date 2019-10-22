package com.m90143.pdm;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class DesafioActivity extends Activity {

    SimpleAdapter adapter;
    private List<String> listGroup;
    private HashMap<String,List<String>> listData;
    private EditText txtValor1;
    private EditText txtValor2;
    private EditText txtResultado;
    private Spinner spnSeletor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);

        txtValor1 = findViewById(R.id.txtValor1);
        txtValor2 = findViewById(R.id.txtValor2);
        txtResultado = findViewById(R.id.txtResultado);

        spnSeletor = findViewById(R.id.spnSeletor);
        ArrayAdapter<CharSequence> adapter_sp = ArrayAdapter.createFromResource(this, R.array.seletor,android.R.layout.simple_spinner_dropdown_item);
        spnSeletor.setAdapter(adapter_sp);


        buildList();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new ExpandableAdapter(DesafioActivity.this, listGroup, listData));

    }

    public void buildList(){
        listGroup = new ArrayList<String>();
        listData = new HashMap<String, List<String>>();

        // GROUP
        listGroup.add("Grupo 1");
        listGroup.add("Grupo 2");
        listGroup.add("Grupo 3");
        listGroup.add("Grupo 4");

        // CHILDREN
        List<String> auxList = new ArrayList<String>();
        auxList.add("Item 1");
        auxList.add("Item 2");
        auxList.add("Item 3");
        auxList.add("Item 4");
        listData.put(listGroup.get(0), auxList);

        auxList = new ArrayList<String>();
        auxList.add("Item 5");
        auxList.add("Item 6");
        auxList.add("Item 7");
        auxList.add("Item 8");
        listData.put(listGroup.get(1), auxList);

        auxList = new ArrayList<String>();
        auxList.add("Item 9");
        auxList.add("Item 10");
        auxList.add("Item 11");
        auxList.add("Item 12");
        listData.put(listGroup.get(2), auxList);

        auxList = new ArrayList<String>();
        auxList.add("Item 13");
        auxList.add("Item 14");
        auxList.add("Item 15");
        auxList.add("Item 16");
        listData.put(listGroup.get(3), auxList);
    }

    public void btnCalcular(View view) {

        String valor1 = txtValor1.getText().toString();
        String valor2 = txtValor2.getText().toString();

        Double resultado = Double.parseDouble(valor1) * Double.parseDouble(valor2);

        txtResultado.setText(resultado.toString());

        //Map<String,Object> map = new HashMap<>();
        //map.put("valor1", txtValor1.getText().toString());
        //map.put("valor2", txtValor2.getText().toString());
        //map.put("resultado", txtResultado.getText().toString());
        //map.put("seletor", spnSeletor.getItemAtPosition(0).toString());

        txtValor1.setText("");
        txtValor2.setText("");

        //adapter.notifyDataSetChanged();
    }
}
