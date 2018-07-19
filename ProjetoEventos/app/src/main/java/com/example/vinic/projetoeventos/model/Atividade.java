package com.example.vinic.projetoeventos.model;

import java.util.Date;

public class Atividade {

    private String nome;
    private String tipoAtividade;
    private String horaInicio;
    private String horaTermino;
    private double valor;

    public Atividade() {
    }


    public Atividade(String nome, String tipoAtividade,String horaInicio, String horaTermino, double valor) {
        this.nome = nome;
        this.tipoAtividade = tipoAtividade;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.valor = valor;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
