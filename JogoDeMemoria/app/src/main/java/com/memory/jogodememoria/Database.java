package com.memory.jogodememoria;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Barbara on 18/12/2016.
 */

public class Database {
    private static String DATABASE_NAME = "bd1";
    private static final int DATABASE_ACCESS = 0;
    private Memoria memoria;

    private static final String SQL_STRUCT = "CREATE TABLE IF NOT EXIST memoria id_ INTEGER PRIMARY KEY AUTOINCREMENT," +
            " tentativas INTEGER, vitorias INTEGER";
    private static final String INSERT = "INSERT INTO memoria VALUES (1,'%d','%d')";
    private static final String SOMA_VITORIAS = "SELECT * FROM memoria";
    private static final String SOMA_TENTATIVAS = "SELECT tentativas FROM memoria";
    private static final String SQL_CLEAR = "DROP TABLE IF EXIST memoria";

    private SQLiteDatabase database;
    private Cursor cursor;
    private int indexID, indexVitorias, indexTentativas;

    public Database(Context context) {
        database = context.openOrCreateDatabase(DATABASE_NAME, DATABASE_ACCESS,null);
        database.execSQL(SQL_STRUCT);
    }

    public void clear(){
        database.execSQL(SQL_CLEAR);
    }

    public void fechar(){
        database.close();
    }

    public void insert(Memoria memoria){
        String query = String.format(INSERT, memoria.getTentativas(), memoria.getVitorias());
        database.execSQL(query);
    }

    public Memoria qtdeVitoriasETentarivas(){
        Memoria memoria = new Memoria();
        cursor = database.rawQuery(SOMA_VITORIAS, null);
        if(cursor.moveToFirst()){
            indexID = cursor.getColumnIndex("id");
            indexVitorias = cursor.getColumnIndex("vitorias");
            indexTentativas = cursor.getColumnIndex("tentativas");
        }
        memoria.setTentativas(cursor.getInt(indexTentativas));
        memoria.setVitorias(cursor.getInt(indexVitorias));
        cursor.close();
        return memoria;
    }

}
