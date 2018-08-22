package com.example.vinic.projetoeventos.model;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cupom {

    private String codigo;
    private int quant;
    private Date inicio;
    private Date termino;
    private double porcentagem;

    public Cupom() {
    }

    public Cupom(String codigo,int quant, Date inicio, Date termino, double porcentagem) {
        this.codigo = codigo;
        this.quant = quant;
        this.inicio = inicio;
        this.termino = termino;
        this.porcentagem = porcentagem;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }
}
