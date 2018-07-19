package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Evento;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventoCases {

    public static boolean cadastrarEvento(String nome, String tipo, String local, Date dataInicio, Date dataFinal, int quant, String key){
        String keyEvent = ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().getKey();
        Evento evento = new Evento(keyEvent,nome,tipo,local,dataInicio,dataFinal,quant,key);
        Map<String, Object> usuarioValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        return true;
    }

}
