package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Inscricao;

public class InscricaoCases {

    public static boolean inserirAtividadeNaInscricao(String keyUsuario,String keyEvento,Atividade atividade){
        Inscricao inscricao = new Inscricao(keyUsuario,keyEvento);
        ConfiguracaoFirebase.salvarInscricaoFirebase(keyUsuario,keyEvento,atividade,inscricao);
        return true;
    }

}
