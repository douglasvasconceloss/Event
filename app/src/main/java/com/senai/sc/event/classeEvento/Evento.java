package com.senai.sc.event.classeEvento;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String nomeEvento;
    private String dataEvento;
    private Locais locais;

    public Evento (int id, String nomeEvento, String dataEvento, Locais locais) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.locais = locais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Locais getLocais() {
        return locais;
    }

    public void setLocais(Locais locais) {
        this.locais = locais;
    }


    @Override
    public String toString() {
        return nomeEvento + " | " + dataEvento + " | " + locais.getNome();
    }
}
