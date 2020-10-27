package com.senai.sc.event.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGateway;
    private SQLiteDatabase db;

    public static DBGateway getInstance(Context context) {
        if (dbGateway == null) {
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    public DBGateway(Context context) {
        DataBaseDBHelper dbHelper = new DataBaseDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }
}
