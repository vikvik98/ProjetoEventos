package com.example.vinic.projetoeventos.model;

public class Tag {

    public Tag(String nome) {
        this.nome = nome;
    }

    public Tag() {
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
