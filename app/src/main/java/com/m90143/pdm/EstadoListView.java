package com.m90143.pdm;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class EstadoListView extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    String [] cidades_RS = {"Venâncio Aires", "Santa Cruz do Sul", "Lajeado"};
    String [] cidades_SC = {"Blumenau", "Florianópolis", "Joinville"};
    String [] cidades_PR = {"Curitiba", "Francisco Beltrão", "Pato Branco"};
    ArrayAdapter<CharSequence> adapter_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_listview);

        TextView txtPessoa = findViewById(R.id.txtPessoa);

        txtPessoa.setText(getIntent().getStringExtra("Pessoa"));

        spinner = (Spinner) findViewById(R.id.spinner);
        listView =  (ListView) findViewById(R.id.listview);

        adapter_sp = ArrayAdapter.createFromResource(this, R.array.estados,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_sp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                AtualizaList(selectedItem);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }


    private void AtualizaList(String item){
        if(item.equals("RS"))
        {
            ArrayAdapter<String> adapter_lst = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_RS));
            listView.setAdapter(adapter_lst);
        }
        else if(item.equals("SC"))
        {
            ArrayAdapter<String> adapter_lst = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_SC));
            listView.setAdapter(adapter_lst);
            //final ArrayAdapter<String> adapter_lst = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_SC));
            //listView.setAdapter(adapter_lst);
        }
        if(item.equals("PR"))
        {
            ArrayAdapter<String> adapter_lst = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_PR));
            listView.setAdapter(adapter_lst);
            //final ArrayAdapter<String> adapter_lst = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades_PR));
            //listView.setAdapter(adapter_lst);
        }
    }

}
