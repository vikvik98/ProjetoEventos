package com.example.vinic.projetoeventos.controller;

import android.widget.EditText;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;



public class UsuarioController {


    public UsuarioController() {

    }

    public void cadastrarAluno(EditText nome, EditText senha, EditText email){
        String nomeUsuario = nome.getText().toString();
        String senhaUsuario = senha.getText().toString();
        String emailUsuario = email.getText().toString();
        Usuario usuario = new Usuario(nomeUsuario,senhaUsuario,emailUsuario);

        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().setValue(usuario);


    }


}
