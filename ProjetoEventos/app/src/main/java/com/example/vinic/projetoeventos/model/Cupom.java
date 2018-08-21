package com.example.vinic.projetoeventos.model;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cupom {
    private String codigo = "AAA";
    private int numero = 001;
    private final String codigoFinal = this.codigo;
    private final int numeroFinal = this.numero;
    private Date inicio;
    private Date termino;
    private float porcentagem;

    public Cupom() {
    }

    public Cupom(Date inicio, Date termino, float porcentagem) {
        this.inicio = inicio;
        this.termino = termino;
        this.porcentagem = porcentagem;
        mudarCodigo();

    }

    private void mudarCodigo(){
        if (this.numero < 999){
            this.codigo = "BBB";
        }else{
            this.numero++;
        }
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codigoFinal", codigoFinal);
        result.put("numeroFinal", numeroFinal);
        result.put("inicio", inicio);
        result.put("termino", termino);
        result.put("porcentagem", porcentagem);

        return result;
    }
}
