package com.example.vinic.projetoeventos.cases;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoCases {

    public static List<Evento> eventosList;
    public static DatabaseReference databaseReferenceEvento = ConfiguracaoFirebase.getDatabaseReference().child("eventos");

    public static void cadastrarAtividade(Evento evento,String nome,String tipoAtividade,String horaInicio,String horaTermino,double valor){
        String keyEvent = evento.getId();
        Atividade atividade = new Atividade(nome,tipoAtividade,horaInicio,horaTermino,valor);
        evento.getAtividades().add(atividade);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);

    }

    public static boolean cadastrarEvento(String nome, String tipo, String local, Date dataInicio, Date dataFinal, int quant, String key){
        String keyEvent = ConfiguracaoFirebase.getDatabaseReference().child("eventos").push().getKey();
        Evento evento = new Evento(keyEvent,nome,tipo,local,dataInicio,dataFinal,quant,key);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        return true;
    }


    public static void pegarEventosNoFirebase(){
        eventosList = new ArrayList<>();
        databaseReferenceEvento.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventosList.clear();
                for (DataSnapshot postSnapsshot : dataSnapshot.getChildren()){
                    Evento evento = postSnapsshot.getValue(Evento.class);
                    eventosList.add(evento);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
