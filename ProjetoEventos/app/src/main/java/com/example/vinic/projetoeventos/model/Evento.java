package com.example.vinic.projetoeventos.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evento {

    private String nome;
    private String id;
    private Date dataInicial;
    private Date dataFinal;
    private int quantPessoas;
    private String statusDoEvento;
    private String local;
    private String tipoEvento;
    private List<String> instituicoes;
    private List<Atividade> atividades = new ArrayList<>();
    private List<Cupom> cupons = new ArrayList<>();
    private List<Tag> tags  = new ArrayList<>();
    private String keyCriador;

    public Evento() {

    }

    public Evento(String id, String nome, String tipoEvento, String local, Date dataInicio, Date dataFinal, int quantPessoas, String keyCriador) {

        this.id = id;
        this.nome = nome;
        this.dataInicial = dataInicio;
        this.dataFinal = dataFinal;
        this.local = local;
        this.tipoEvento = tipoEvento;
        this.statusDoEvento = "Aberto";
        this.quantPessoas = quantPessoas;
        this.keyCriador = keyCriador;
    }

    public String getKeyCriador() {
        return keyCriador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public String getStatusDoEvento() {
        return statusDoEvento;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("nome", nome);
        result.put("dataInicial", dataInicial);
        result.put("dataFinal", dataFinal);
        result.put("quantPessoas", quantPessoas);
        result.put("statusDoEvento", statusDoEvento);
        result.put("local", local);
        result.put("tipoEvento", tipoEvento);
        result.put("instituicoes", instituicoes);
        result.put("atividades", atividades);
        result.put("tags", tags);
        result.put("keyCriador", keyCriador);
        result.put("cupons", cupons);


        return result;
    }
}
