package com.example.vinic.projetoeventos.dao;

import android.support.annotation.NonNull;

import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.cases.InscricaoCases;
import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Cupom;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Inscricao;
import com.example.vinic.projetoeventos.model.Tag;
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
    public static DatabaseReference databaseReferenceEvento = getDatabaseReference().child("eventos");
    public static DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference().child("usuarios");
    public static DatabaseReference databaseReferenceInscricao = getDatabaseReference().child("inscricoes");
    public static List<Evento> eventosList;
    public static List<Usuario> usuarios;
    public static List<Inscricao> inscricoes;


    public static DatabaseReference getDatabaseReference() {
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;

    }


    public static Boolean salvarUsuarioFirebase(Usuario usuario){
        String keyUser = ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().getKey();
        usuario.setId(keyUser);
        Map<String, Object> usuarioValues = usuario.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + keyUser,usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        return true;

    }




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

    public static boolean salvarInscricaoFirebase(Inscricao inscricao,Atividade atividade){
        for (int i = 0; i < inscricoes.size(); i++) {
            if(inscricoes.get(i).getKeyEvento().equals(inscricao.getKeyEvento()) && inscricoes.get(i).getKeyUsuario().equals(inscricao.getKeyUsuario())){
                String keyInscricao = inscricoes.get(i).getId();
                inscricoes.get(i).getAtividades().add(atividade);
                InscricaoCases.calcularValorTotal(inscricoes.get(i));
                Map<String, Object> inscricaoValues = inscricoes.get(i).toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/inscricoes/" + keyInscricao,inscricaoValues);
                ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
                return true;
            }
        }



        String keyInscricao = ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").push().getKey();
        inscricao.setId(keyInscricao);
        inscricao.getAtividades().add(atividade);
        InscricaoCases.calcularValorTotal(inscricao);
        Map<String, Object> inscricaoValues = inscricao.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/inscricoes/" + keyInscricao,inscricaoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        inscricoes.add(inscricao);
        return true;
    }


    public static void salvarAtividadeFirebase(Evento evento,Atividade atividade){
        String keyEvent = evento.getId();
        atividade.setKeyCriador(evento.getKeyCriador());
        atividade.setKeyEvento(evento.getId());
        evento.getAtividades().add(atividade);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);

    }

    public static boolean salvarEventoFirebase(Evento evento){
        String keyEvent = ConfiguracaoFirebase.getDatabaseReference().child("eventos").push().getKey();
        evento.setId(keyEvent);
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

    public static void salvarCupomFirebase(Evento evento,Cupom cupom){
        String keyEvent = evento.getId();
        evento.getCupons().add(cupom);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }

    public static void salvarTag(Tag tag){
        String keyId = MainActivity.usuarioLogado.getId();
        MainActivity.usuarioLogado.getTags().add(tag);
        Map<String, Object> usuarioValues = MainActivity.usuarioLogado.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + keyId,usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);

    }

    public static void salvarColaboradores(Usuario usuario, Evento evento) {
        String keyEvent = evento.getId();
        evento.getColaboradores().add(usuario);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }

    public static List<Evento> pegarColaboracoes(Usuario usuarioLogado){
        List<Evento> eventosColaboradores = new ArrayList<>();
        for (int i = 0; i < eventosList.size(); i++){
            for (int j = 0; j < eventosList.get(i).getColaboradores().size(); j++){
                if (eventosList.get(i).getColaboradores().get(j).getId().equals(usuarioLogado.getId())){
                    eventosColaboradores.add(eventosList.get(i));
                }
            }
        }
        return eventosColaboradores;
    }

    public static List<Evento> pegarMeusEventos(Usuario usuarioLogado) {
        List<Evento> meusEventos = new ArrayList<>();
        for (int i = 0; i <eventosList.size(); i++){
            if (eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())){
                meusEventos.add(eventosList.get(i));

            }
        }
        return meusEventos;
    }

    public static List<Evento> pegarEventos(Usuario usuarioLogado){
        List<Evento> eventosGeral = new ArrayList<>();
        for (int i = 0; i < eventosList.size(); i++){
            if (!eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())){
                eventosGeral.add(eventosList.get(i));

            }
        }
        return eventosGeral;
    }

    public static List<Evento> pegarInscricoes(Usuario usuarioLogado){
        List<Evento> eventosInscritos = new ArrayList<>();
        for (int i = 0; i < inscricoes.size(); i++) {
            if(inscricoes.get(i).getKeyUsuario().equals(usuarioLogado.getId())){
                for (int j = 0; j < eventosList.size(); j++) {
                    if (inscricoes.get(i).getKeyEvento().equals(eventosList.get(j).getId())){
                        Evento evento = new Evento(eventosList.get(j).getNome(),eventosList.get(j).getTipoEvento(),eventosList.get(j).getLocal(),eventosList.get(j).getDataInicial(),eventosList.get(j).getDataFinal(),eventosList.get(j).getQuantPessoas(),eventosList.get(j).getKeyCriador());
                        evento.setAtividades(inscricoes.get(i).getAtividades());
                        eventosInscritos.add(evento);
                    }
                }
            }
        }
        return eventosInscritos;
    }

    public static void atualizarCupom(Evento evento, Cupom cupom){
        String keyEvent = evento.getId();
        cupom.setQuant(cupom.getQuant()-1);
        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + keyEvent,eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }

    public static String nomeUsuario(String keyUsuario){
        for (int i = 0; i < usuarios.size(); i++){
            if (keyUsuario.equals(usuarios.get(i).getId())){
                return usuarios.get(i).getNome();
            }
        }
        return null;
    }

    public static List<Inscricao> pegarInscricoes(String keyEvento){
        List<Inscricao> inscricaoList = new ArrayList<>();
        for (int i = 0; i < inscricoes.size(); i++){
            if (inscricoes.get(i).getKeyEvento().equals(keyEvento)){
                inscricaoList.add(inscricoes.get(i));
            }
        }
        return inscricaoList;
    }

}
