package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Cupom;
import com.example.vinic.projetoeventos.model.Evento;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CupomCases {

    public static void cadastrarCupom(Evento evento,Date dataInicio, Date dataFinal, int percentual){
        String keyEvent = evento.getId();
        Cupom cupom = new Cupom(dataInicio,dataFinal,percentual);
        evento.getCupons().add(cupom);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);

    }
}
