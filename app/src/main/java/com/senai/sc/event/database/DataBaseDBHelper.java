package com.senai.sc.event.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.senai.sc.event.database.contract.EventoContract;
import com.senai.sc.event.database.contract.LocaisContract;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.EventosLocais";
    private static final int DATABASE_VERSION = 1;


    public DataBaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocaisContract.criarTabela());
        db.execSQL(EventoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventoContract.removerTabela());
        db.execSQL(LocaisContract.removerTabela());
        db.execSQL(LocaisContract.criarTabela());
        db.execSQL(EventoContract.criarTabela());
    }
}
