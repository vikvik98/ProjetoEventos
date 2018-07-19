package com.example.vinic.projetoeventos.cases;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoCases {

    private List<Evento> eventosList;

    public static boolean cadastrarEvento(String nome, String tipo, String local, Date dataInicio, Date dataFinal, int quant, String key){
        String keyEvent = ConfiguracaoFirebase.getDatabaseReference().child("eventos").push().getKey();
        Evento evento = new Evento(keyEvent,nome,tipo,local,dataInicio,dataFinal,quant,key);
        Map<String, Object> usuarioValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        return true;
    }


    public List<Evento> pegarEventosNoFirebase(){
        eventosList = new ArrayList<>();
        eventosList.clear();
        ConfiguracaoFirebase.getDatabaseReference().child("eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapsshot : dataSnapshot.getChildren()){
                    Evento evento = postSnapsshot.getValue(Evento.class);
                    eventosList.add(evento);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("certo" , ""  + eventosList.size() + "ruim");
        return eventosList;
    }

}
