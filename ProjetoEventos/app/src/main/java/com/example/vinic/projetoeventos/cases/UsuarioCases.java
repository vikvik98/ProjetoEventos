package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;

public class UsuarioCases {

    public static Boolean cadastrarUsuario(String nome, String senha, String email){

        Usuario usuario = new Usuario(nome,senha,email);
        ConfiguracaoFirebase.salvarUsuarioFirebase(usuario);
        MainActivity.usuarioLogado = usuario;
        return true;

    }

    public static Usuario logarUsuario(String email){
        for (int i = 0; i < ConfiguracaoFirebase.usuarios.size(); i++) {
            if(ConfiguracaoFirebase.usuarios.get(i).getEmail().equals(email)){
                return ConfiguracaoFirebase.usuarios.get(i);
            }
        }
        return null;
    }

}
