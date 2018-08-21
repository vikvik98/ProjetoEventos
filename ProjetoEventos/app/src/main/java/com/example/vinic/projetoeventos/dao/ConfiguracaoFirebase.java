package com.example.vinic.projetoeventos.dao;

import android.support.annotation.NonNull;

import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Cupom;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Inscricao;
import com.example.vinic.projetoeventos.model.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfiguracaoFirebase {

    public static DatabaseReference database;

    public static DatabaseReference getDatabaseReference() {
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;

    }

    public static DatabaseReference databaseReferenceEvento = getDatabaseReference().child("eventos");
    public static List<Evento> eventosList;
    public static List<Usuario> usuarios;
    public static DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference().child("usuarios");
    public static List<Inscricao> inscricoes;
    public static DatabaseReference databaseReferenceInscricao = getDatabaseReference().child("inscricoes");


    public static Boolean cadastrarUsuario(String nome, String senha, String email){
        String keyUser = ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().getKey();
        Usuario usuario = new Usuario(keyUser,nome,senha,email);
        Map<String, Object> usuarioValues = usuario.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + keyUser,usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        MainActivity.usuarioLogado = usuario;
        return true;

    }

    public static Usuario logarUsuario(String email){
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getEmail().equals(email)){
                return usuarios.get(i);
            }
        }
        return null;
    }


    //TODO: perguntar para o prof se faz mais sentido retornar uma lista de usuarios ou colocar dentro de um atributo.
    public static void pegarUsuariosNoFirebase(){
        usuarios = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarios.clear();
                for (DataSnapshot postSnapsshot : dataSnapshot.getChildren()){
                    Usuario user = postSnapsshot.getValue(Usuario.class);
                    usuarios.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void pegarInscricoesNoFirebase(){
        inscricoes = new ArrayList<>();

        databaseReferenceInscricao.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                inscricoes.clear();
                for (DataSnapshot postSnapsshot : dataSnapshot.getChildren()){
                    Inscricao inscricao = postSnapsshot.getValue(Inscricao.class);
                    inscricoes.add(inscricao);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static boolean inserirAtividadeNaInscricoes(String keyUsuario,String keyEvento,Atividade atividade){
        for (int i = 0; i < inscricoes.size(); i++) {
            if(inscricoes.get(i).getKeyEvento().equals(keyEvento)){
                String keyInscricao = inscricoes.get(i).getId();
                inscricoes.get(i).getAtividades().add(atividade);
                Map<String, Object> inscricaoValues = inscricoes.get(i).toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/inscricoes/" + keyInscricao,inscricaoValues);
                ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
                return true;
            }
        }
        String keyInscricao = ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").push().getKey();
        Inscricao inscricao = new Inscricao(keyEvento,keyUsuario,keyEvento);
        inscricao.getAtividades().add(atividade);
        Map<String, Object> inscricaoValues = inscricao.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/inscricoes/" + keyInscricao,inscricaoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        return true;
    }


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
