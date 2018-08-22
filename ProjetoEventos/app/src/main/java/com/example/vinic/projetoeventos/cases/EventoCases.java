package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;

import com.example.vinic.projetoeventos.model.Evento;



public class EventoCases {






    public static Evento pegarEvento(String id){
        for (int i = 0; i <= ConfiguracaoFirebase.eventosList.size()-1; i++) {
            if (ConfiguracaoFirebase.eventosList.get(i).getId().equals(id)) {
                return ConfiguracaoFirebase.eventosList.get(i);
            }
        }
        return null;
    }

}
