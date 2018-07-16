package com.example.vinic.projetoeventos.controller;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class UsuarioController {

    public UsuarioController() {

    }

    public static void cadastrarUsuario(EditText nome, EditText senha, EditText email){
        String nomeUsuario = nome.getText().toString();
        String senhaUsuario = senha.getText().toString();
        String emailUsuario = email.getText().toString();

        Usuario usuario = new Usuario(nomeUsuario,senhaUsuario,emailUsuario);

        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().setValue(usuario);

    }





}
