package com.senai.sc.event.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.senai.sc.event.classeEvento.Evento;
import com.senai.sc.event.database.entity.EventoEntity;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvarEvento(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNomeEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getDataEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_LOCAL, evento.getLocalEvento());
        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME, contentValues, EventoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean excluirEvento(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNomeEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getDataEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_LOCAL, evento.getLocalEvento());

        if (evento.getId() > 0) {
            return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return false;

    }

    public List<Evento> listarEventos() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String dataEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            String localEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL));
            eventos.add(new Evento(id, nomeEvento, dataEvento, localEvento));
        }
        cursor.close();
        return eventos;
    }

}
