package com.senai.sc.event.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.senai.sc.event.database.contract.EventoContract;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.evento";
    private static final int DATABSE_VERSION = 1;


    public DataBaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventoContract.removerTabela());
        db.execSQL(EventoContract.criarTabela());
    }
}
