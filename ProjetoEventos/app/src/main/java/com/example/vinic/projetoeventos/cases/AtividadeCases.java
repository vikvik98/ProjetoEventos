package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;

public class AtividadeCases {

    public static void cadastrarAtividade(Evento evento, String nome, String tipoAtividade, String horaInicio, String horaTermino, double valor){

        Atividade atividade = new Atividade(nome,tipoAtividade,horaInicio,horaTermino,valor);
        ConfiguracaoFirebase.salvarAtividadeFirebase(evento,atividade);

    }

}
