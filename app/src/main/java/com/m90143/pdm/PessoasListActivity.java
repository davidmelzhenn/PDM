package com.m90143.pdm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;


public class PessoasListActivity extends ListActivity {

    String [] values = {"Pedro", "Tiago", "João", "Paulo", "Cesar", "Henrique", "Guilherme"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoas_list_activity);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(values));
        setListAdapter(adapter);

        ListView li = getListView();
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CarregaTela(values[i]);
            }
        });

    }

    private void CarregaTela(String pessoa){
        Intent intent = new Intent(this, EstadoListView.class);
        intent.putExtra("Pessoa", pessoa);
        startActivity(intent);
    }

}
