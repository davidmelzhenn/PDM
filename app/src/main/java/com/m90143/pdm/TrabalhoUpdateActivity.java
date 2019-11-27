package com.m90143.pdm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrabalhoUpdateActivity extends AppCompatActivity {

    String idDados = "";
    private DatabaseTrabalho database;
    private EditText nome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho_update);

        Bundle extras = getIntent().getExtras();
        idDados = extras.getString("idDados");

        nome = (EditText) findViewById(R.id.txtNome);

        database = new DatabaseTrabalho(this);

        String sql = "SELECT * FROM recorder WHERE id = " + idDados;
        preencheCampos(sql);

    }

    private void preencheCampos(String bySQL){
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.rawQuery( bySQL, null);
        cursor.moveToFirst();
        nome.setText(cursor.getString(1));
    }

    public void btnAtualizar(View view){
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", nome.getText().toString());

        String where[] = new String[] {idDados};
        long resultado = db.update("recorder", values, "id = ?", where);
        if (resultado != -1){
            Toast.makeText(this, "Registro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else{
            Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnExcluir(View view){
        SQLiteDatabase db = database.getWritableDatabase();
        String where[] = new String[] {idDados};

        long resultado = db.delete("recorder", "id = ?", where);
        if (resultado != -1){
            Toast.makeText(this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else{
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        database.close();
        super.onDestroy();
    }
}
