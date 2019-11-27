package com.m90143.pdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTrabalho extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "trabalho";
    private static int VERSAO = 1;

    public DatabaseTrabalho(Context context){
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE recorder (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome TEXT, " +
                "datahora TEXT, " +
                "latitude TEXT, " +
                "longitude TEXT, " +
                "audio BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS recorder");
    }
}