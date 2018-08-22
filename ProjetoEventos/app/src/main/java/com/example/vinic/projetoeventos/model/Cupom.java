package com.example.vinic.projetoeventos.model;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cupom {

    private static int numero = 1;
    private final int numeroFinal = this.numero;
    private Date inicio;
    private Date termino;
    private double porcentagem;

    public Cupom() {
    }

    public Cupom(Date inicio, Date termino, double porcentagem) {
        this.inicio = inicio;
        this.termino = termino;
        this.porcentagem = porcentagem;
        mudarCodigo();

    }

    private void mudarCodigo(){
        numero++;
    }

    public static int getNumero() {
        return numero;
    }

    public static void setNumero(int numero) {
        Cupom.numero = numero;
    }

    public int getNumeroFinal() {
        return numeroFinal;
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
