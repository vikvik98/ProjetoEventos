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
        String keyUser = ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().getKey();
        Usuario usuario = new Usuario(keyUser,nome,senha,email);
        Map<String, Object> usuarioValues = usuario.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + keyUser,usuarioValues);
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
