package com.example.vinic.projetoeventos.controller;

import android.widget.EditText;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    public static int id = 0;

    public UsuarioController() {

    }

    public void cadastrarAluno(EditText nome, EditText senha, EditText email){
        String nomeUsuario = nome.getText().toString();
        String senhaUsuario = senha.getText().toString();
        String emailUsuario = email.getText().toString();
        Usuario usuario = new Usuario(nomeUsuario,senhaUsuario,emailUsuario);
        String idUser = Integer.toString(id);

        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").child(idUser).setValue(usuario);
        id += 1;


    }
}
