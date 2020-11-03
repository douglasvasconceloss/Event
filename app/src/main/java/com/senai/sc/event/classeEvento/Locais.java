package com.senai.sc.event.classeEvento;

import java.io.Serializable;

public class Locais implements Serializable {

    private int id;
    private String nome;
    private String bairro;
    private String cidade;
    private int capacidadePublico;

    public Locais(int id, String nome, String bairro, String cidade, int capacidadePublico) {
        this.id = id;
        this.nome = nome;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidadePublico = capacidadePublico;
    }

    public Locais(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCapacidadePublico() {
        return capacidadePublico;
    }

    public void setCapacidadePublico(int capacidadePublico) {
        this.capacidadePublico = capacidadePublico;
    }

    @Override
    public String toString() {
        return nome + ", " + bairro  + ", " + cidade + " | " + "Capacidade de " + capacidadePublico + " pessoas.";
    }
}
