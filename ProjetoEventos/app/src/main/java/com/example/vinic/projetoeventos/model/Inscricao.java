package com.example.vinic.projetoeventos.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inscricao {

    private String id;
    private String keyUsuario;
    private String keyEvento;
    private double valor;
    private List<Atividade> atividades = new ArrayList<>();
    private boolean pago;

    public Inscricao() {
    }

    public Inscricao(String keyUsuario, String keyEvento) {
        this.keyUsuario = keyUsuario;
        this.keyEvento = keyEvento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getKeyUsuario() {
        return keyUsuario;
    }

    public void setKeyUsuario(String keyUsuario) {
        this.keyUsuario = keyUsuario;
    }

    public String getKeyEvento() {
        return keyEvento;
    }

    public void setKeyEvento(String keyEvento) {
        this.keyEvento = keyEvento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("keyUsuario", keyUsuario);
        result.put("keyEvento", keyEvento);
        result.put("valor", valor);
        result.put("atividades", atividades);
        result.put("pago", pago);

        return result;
    }

}
