package com.example.vinic.projetoeventos.cases;


import com.example.vinic.projetoeventos.app.LoginActivity;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UsuarioCases {


    public static Boolean cadastrarUsuario(String nome, String senha, String email){
        String key = ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().getKey();
        Usuario usuario = new Usuario(key,nome,senha,email);
        Map<String, Object> usuarioValues = usuario.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + key,usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        LoginActivity.usuario = usuario;
        return true;

    }

    public static Usuario logarUsuario(String email){
        for (int i = 0; i < LoginActivity.usuarios.size(); i++) {
            if(LoginActivity.usuarios.get(i).getEmail().equals(email)){
                return LoginActivity.usuarios.get(i);
            }
        }
        return null;
    }

    public static boolean cadastrarEvento(String nome,String data, String local,String tipoEvento){
        Date dataFormatada = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        try {
            dataFormatada = format.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Evento evento = new Evento(nome,dataFormatada,local,tipoEvento);
        LoginActivity.usuario.getEventos().add(evento);
        Map<String, Object> usuarioValues = LoginActivity.usuario.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + LoginActivity.usuario.getId(),usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
        return true;
    }

}
