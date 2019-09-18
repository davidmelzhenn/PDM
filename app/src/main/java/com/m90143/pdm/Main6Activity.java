package com.m90143.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Main6Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main6);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.meumenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.itemPessoas:
                Toast.makeText(this, "Pessoas!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main6Activity.this, PessoasListActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemSave:
                Toast.makeText(this, "Salvar!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemAdd:
                Toast.makeText(this, "Adicionar", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

}
