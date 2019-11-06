package com.m90143.pdm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    String idDados = "";
    private DatabaseHelper helper;
    private EditText modelo, valor, ano;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Bundle extras = getIntent().getExtras();
        idDados = extras.getString("idDados");

        modelo = (EditText) findViewById(R.id.txtModelo);
        valor = (EditText) findViewById(R.id.txtvalor);
        ano = (EditText) findViewById(R.id.txtAno);

        helper = new DatabaseHelper(this);

        String sql = "SELECT * FROM carro WHERE id = " + idDados;
        preencheCampos(sql);

    }

    private void preencheCampos(String bySQL){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery( bySQL, null);
        cursor.moveToFirst();
        modelo.setText(cursor.getString(1));
        ano.setText(String.valueOf(cursor.getInt(2)));
        valor.setText(String.valueOf(cursor.getDouble(3)));
    }

    public void atualizarCarro(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        String where[] = new String[] {idDados};
        long resultado = db.update("carro", values, "id = ?", where);
        if (resultado != -1){
            Toast.makeText(this, "Registro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else{
            Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
        }
    }

    public void excluirCarro(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        String where[] = new String[] {idDados};

        long resultado = db.delete("carro", "id = ?", where);
        if (resultado != -1){
            Toast.makeText(this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else{
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
