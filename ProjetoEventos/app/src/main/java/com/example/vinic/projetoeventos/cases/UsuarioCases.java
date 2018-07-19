package com.example.vinic.projetoeventos.cases;


import android.support.annotation.NonNull;

import com.example.vinic.projetoeventos.app.LoginActivity;
import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioCases {

    public static List<Usuario> usuarios;
    public static DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference().child("usuarios");


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


}
