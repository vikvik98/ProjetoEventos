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
    private List<Tag> tags;
    private String keyCriador;

    public Evento() {

    }


    public Evento(String id, String nome, String local, String tipoEvento, Date dataInicio, Date dataFinal, int quantPessoas, String keyCriador) {

        this.id = id;
        this.nome = nome;
        this.dataInicial = dataInicio;
        this.dataFinal = dataFinal;
        this.local = local;
        this.tipoEvento = tipoEvento;
        this.statusDoEvento = "Aberto";
        this.quantPessoas = quantPessoas;
        this.keyCriador = keyCriador;
        //this.atividades = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public String getKeyCriador() {
        return keyCriador;
    }

    public void setKeyCriador(String keyCriador) {
        this.keyCriador = keyCriador;
    }

    public List<String> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<String> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(int quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getStatusDoEvento() {
        return statusDoEvento;
    }

    public void setStatusDoEvento(String statusDoEvento) {
        this.statusDoEvento = statusDoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("nome", nome);
        result.put("dataInicio", dataInicial);
        result.put("dataTermino", dataFinal);
        result.put("quantPessoas", quantPessoas);
        result.put("status", statusDoEvento);
        result.put("local", local);
        result.put("tipo", tipoEvento);
        result.put("instituicoes", instituicoes);
        result.put("atividades", atividades);
        result.put("tags", tags);
        result.put("keyCriador", keyCriador);

        return result;
    }
}
