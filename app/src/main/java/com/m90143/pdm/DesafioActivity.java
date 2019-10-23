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
import android.widget.BaseExpandableListAdapter;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class DesafioActivity extends Activity {

    BaseExpandableListAdapter adapter;
    private List<String> listGroup;
    private HashMap<String,List<String>> listData;
    private List<Map<String,String>> lista;
    private ExpandableListView expandableListView;
    private EditText txtValor1;
    private EditText txtValor2;
    private EditText txtResultado;
    private Spinner spnSeletor;
    String fOperacao = "", fOperacaoNome = "";
    private Integer listCount = 0;

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

        listGroup = new ArrayList<String>();
        listData = new HashMap<String, List<String>>();
        lista = new ArrayList<>();

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        adapter = new ExpandableAdapter(DesafioActivity.this, listGroup, listData, lista);

        expandableListView.setAdapter(adapter);

        spnSeletor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                fOperacao = parent.getItemAtPosition(position).toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    public void btnCalcular(View view) {

        String valor1 = txtValor1.getText().toString();
        String valor2 = txtValor2.getText().toString();

        Double resultado = 0.00;

        if (valor1.isEmpty() == true || valor2.isEmpty() == true ) {
            Toast.makeText(this, "Valores inválidos. Verifique!", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (fOperacao)
        {
            case "+":
                resultado = Double.parseDouble(valor1) + Double.parseDouble(valor2);
                fOperacaoNome = "Soma";
                break;
            case "-":
                resultado = Double.parseDouble(valor1) - Double.parseDouble(valor2);
                fOperacaoNome = "Subtração";
                break;
            case "*":
                resultado = Double.parseDouble(valor1) * Double.parseDouble(valor2);
                fOperacaoNome = "Multiplicação";
                break;
            case "/":
                resultado = Double.parseDouble(valor1) / Double.parseDouble(valor2);
                fOperacaoNome = "Divisão";
                break;
            default:
                resultado = 0.00;
        }

        txtResultado.setText(resultado.toString());

        listGroup.add("Resultado: " + txtResultado.getText().toString());

        List<String> auxList = new ArrayList<String>();
        auxList.add("Operação: " + fOperacao);

        listData.put(listGroup.get(listCount), auxList);
        listCount++;


        Map<String,String> map = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        map.put("datahora", "Data/hora: " + currentDateandTime);
        map.put("valor1", "Valor1: " + txtValor1.getText().toString());
        map.put("valor2", "Valor2: " + txtValor2.getText().toString());
        map.put("resultado", "Resultado: " + txtResultado.getText().toString());
        map.put("operacao", "Operação: " + fOperacaoNome);

        lista.add(map);

        txtValor1.setText("");
        txtValor2.setText("");

        adapter.notifyDataSetChanged();
    }
}
