package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Inscricao;

public class InscricaoCases {

    public static boolean inserirAtividadeNaInscricao(String keyUsuario,String keyEvento,Atividade atividade){
        Inscricao inscricao = new Inscricao(keyUsuario,keyEvento);
        ConfiguracaoFirebase.salvarInscricaoFirebase(inscricao, atividade);
        return true;
    }


    public static void calcularValorTotal(Inscricao inscricao){
        double valor = 0;
        for (int i = 0; i < inscricao.getAtividades().size(); i++) {
            valor += inscricao.getAtividades().get(i).getValor();
        }
        inscricao.setValor(valor);
    }

}
