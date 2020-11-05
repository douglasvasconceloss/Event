package com.senai.sc.event.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.senai.sc.event.MainActivity;
import com.senai.sc.event.classeEvento.Evento;
import com.senai.sc.event.classeEvento.Locais;
import com.senai.sc.event.database.entity.EventoEntity;
import com.senai.sc.event.database.entity.LocaisEntity;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private String textoPesquisaEvento = new MainActivity().textoPesquisaEvento;
    private String textoPesquisaCidade = new MainActivity().textoPesquisaCidade;

    private final String SQL_LISTAR_TODOS = "SELECT evento._id, nomeEvento, dataEvento, idLocais, nomeLocal FROM " + EventoEntity.TABLE_NAME + " INNER JOIN "
            + LocaisEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAIS + " = " + LocaisEntity.TABLE_NAME + "." + LocaisEntity._ID;

    private final String SQL_LISTAR_PESQUISA_EVENTO = "SELECT evento._id, nomeEvento, dataEvento, idLocais, nomeLocal FROM " + EventoEntity.TABLE_NAME + " INNER JOIN "
            + LocaisEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAIS + " = " + LocaisEntity.TABLE_NAME + "." + LocaisEntity._ID +
            " WHERE nomeEvento LIKE '%" + textoPesquisaEvento + "%'";

    private final String SQL_LISTAR_PESQUISA_CIDADE = "SELECT evento._id, nomeEvento, dataEvento, idLocais, nomeLocal, cidadeLocal FROM " + EventoEntity.TABLE_NAME + " INNER JOIN "
            + LocaisEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAIS + " = " + LocaisEntity.TABLE_NAME + "." + LocaisEntity._ID +
            " WHERE cidadeLocal LIKE '%" + textoPesquisaCidade + "%'";

    private final String SQL_ORDENAR_CRESCENTE = "SELECT evento._id, nomeEvento, dataEvento, idLocais, nomeLocal FROM " + EventoEntity.TABLE_NAME + " INNER JOIN "
            + LocaisEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAIS + " = " + LocaisEntity.TABLE_NAME + "." + LocaisEntity._ID
            + " ORDER BY nomeEvento ASC";

    private final String SQL_ORDENAR_DECRESCENTE = "SELECT evento._id, nomeEvento, dataEvento, idLocais, nomeLocal FROM " + EventoEntity.TABLE_NAME + " INNER JOIN "
            + LocaisEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAIS + " = " + LocaisEntity.TABLE_NAME + "." + LocaisEntity._ID
            + " ORDER BY nomeEvento DESC";

    private DBGateway dbGateway;

    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvarEvento(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNomeEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getDataEvento());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAIS, evento.getLocais().getId());
        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME, contentValues, EventoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean excluirEvento(Evento evento) {
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
            int idLocais = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAIS));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocaisEntity.COLUMN_NAME_NOME_LOCAL));
            Locais locais = new Locais(idLocais, nomeLocal);
            eventos.add(new Evento(id, nomeEvento, dataEvento, locais));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> buscarEvento() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_PESQUISA_EVENTO, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String dataEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocais = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAIS));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocaisEntity.COLUMN_NAME_NOME_LOCAL));
            Locais locais = new Locais(idLocais, nomeLocal);
            eventos.add(new Evento(id, nomeEvento, dataEvento, locais));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> buscarCidade() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_PESQUISA_CIDADE, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String dataEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocais = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAIS));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocaisEntity.COLUMN_NAME_NOME_LOCAL));
            Locais locais = new Locais(idLocais, nomeLocal);
            eventos.add(new Evento(id, nomeEvento, dataEvento, locais));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> ordenarEventoCrescente() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_ORDENAR_CRESCENTE, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String dataEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocais = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAIS));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocaisEntity.COLUMN_NAME_NOME_LOCAL));
            Locais locais = new Locais(idLocais, nomeLocal);
            eventos.add(new Evento(id, nomeEvento, dataEvento, locais));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> ordenarEventoDecrescente() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_ORDENAR_DECRESCENTE, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String dataEvento = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocais = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAIS));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocaisEntity.COLUMN_NAME_NOME_LOCAL));
            Locais locais = new Locais(idLocais, nomeLocal);
            eventos.add(new Evento(id, nomeEvento, dataEvento, locais));
        }
        cursor.close();
        return eventos;
    }
}
