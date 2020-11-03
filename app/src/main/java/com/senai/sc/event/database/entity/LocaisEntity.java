package com.senai.sc.event.database.entity;

import android.provider.BaseColumns;

public final class LocaisEntity implements BaseColumns {

    private LocaisEntity() {}

    public static final String TABLE_NAME = "locais";
    public static final String COLUMN_NAME_NOME_LOCAL = "nomeLocal";
    public static final String COLUMN_NAME_BAIRRO = "bairroLocal";
    public static final String COLUMN_NAME_CIDADE = "cidadeLocal";
    public static final String COLUMN_NAME_CAPACIDADE = "capacidadeLocal";

}
