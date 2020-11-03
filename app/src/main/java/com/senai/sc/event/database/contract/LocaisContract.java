package com.senai.sc.event.database.contract;

import com.senai.sc.event.database.entity.LocaisEntity;

public final class LocaisContract {

    private LocaisContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + LocaisEntity.TABLE_NAME + " (" + LocaisEntity._ID + " INTEGER PRIMARY KEY," + LocaisEntity.COLUMN_NAME_NOME_LOCAL + " TEXT," +
                LocaisEntity.COLUMN_NAME_BAIRRO + " TEXT," + LocaisEntity.COLUMN_NAME_CIDADE + " TEXT," + LocaisEntity.COLUMN_NAME_CAPACIDADE + " TEXT)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS" + LocaisEntity.TABLE_NAME;
    }
}
