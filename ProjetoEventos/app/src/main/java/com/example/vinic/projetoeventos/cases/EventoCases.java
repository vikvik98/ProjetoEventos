package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;

import com.example.vinic.projetoeventos.model.Evento;

import java.util.Date;


public class EventoCases {


    public static boolean cadastrarEvento(String nome, String tipo, String local, Date dataInicio, Date dataFinal, int quant, String key){

        Evento evento = new Evento(nome,tipo,local,dataInicio,dataFinal,quant,key);
        ConfiguracaoFirebase.salvarEventoFirebase(evento);
        return true;

    }



    public static Evento pegarEvento(String id){
        for (int i = 0; i <= ConfiguracaoFirebase.eventosList.size()-1; i++) {
            if (ConfiguracaoFirebase.eventosList.get(i).getId().equals(id)) {
                return ConfiguracaoFirebase.eventosList.get(i);
            }
        }
        return null;
    }

}
