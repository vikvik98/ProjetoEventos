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
    private List<Evento> eventos = new ArrayList<>();
    private List<Usuario> colaboradores = new ArrayList<>();
    private String keyCriador;

    public Evento() {

    }

    public Evento( String nome, String tipoEvento, String local, Date dataInicio, Date dataFinal, int quantPessoas, String keyCriador) {

        this.nome = nome;
        this.dataInicial = dataInicio;
        this.dataFinal = dataFinal;
        this.local = local;
        this.tipoEvento = tipoEvento;
        this.statusDoEvento = "Aberto";
        this.quantPessoas = quantPessoas;
        this.keyCriador = keyCriador;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(int quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

    public void setStatusDoEvento(String statusDoEvento) {
        this.statusDoEvento = statusDoEvento;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public List<String> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<String> instituicoes) {
        this.instituicoes = instituicoes;
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

    public List<Usuario> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void setKeyCriador(String keyCriador) {
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
        result.put("eventos", eventos);
        result.put("colaboradores", colaboradores);


        return result;
    }
}
