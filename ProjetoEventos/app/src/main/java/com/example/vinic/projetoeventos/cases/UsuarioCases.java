package com.example.vinic.projetoeventos.cases;


import com.example.vinic.projetoeventos.app.LoginActivity;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;

public class UsuarioCases {


    public static Boolean cadastrarUsuario(String nome, String senha, String email){
        Usuario usuario = new Usuario(nome,senha,email);
        LoginActivity.usuario = usuario;
        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").push().setValue(usuario);
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

}
