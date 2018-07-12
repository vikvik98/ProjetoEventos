package com.example.vinic.projetoeventos.model;

import java.util.Date;
import java.util.List;

public class Evento {

    private String nome;
    private Date data;
    private String statusDoEvento;
    private String descricao;
    private String tipoEvento;
    private List<Instituicao> instituicoes;
    private List<Atividade> atividades;
    private List<Tag> tags;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatusDoEvento() {
        return statusDoEvento;
    }

    public void setStatusDoEvento(String statusDoEvento) {
        this.statusDoEvento = statusDoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
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
}
