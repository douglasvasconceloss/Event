package com.senai.sc.event.classeEvento;

public class Evento {

    private int id;
    private String nomeEvento;
    private String dataEvento;
    private String localEvento;

    public Evento(int id, String nomeEvento, String dataEvento, String localEvento) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.localEvento = localEvento;
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

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    @Override
    public String toString() {
        return nomeEvento + " | " + dataEvento + " | " + localEvento;
    }
}
