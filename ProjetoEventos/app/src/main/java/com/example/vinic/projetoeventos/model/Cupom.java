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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("numeroFinal", numeroFinal);
        result.put("inicio", inicio);
        result.put("termino", termino);
        result.put("porcentagem", porcentagem);

        return result;
    }
}
