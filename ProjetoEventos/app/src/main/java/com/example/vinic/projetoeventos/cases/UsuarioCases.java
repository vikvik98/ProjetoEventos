package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Tag;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;

public class UsuarioCases {

    public static boolean cadastrarUsuario(String nome, String senha, String email){

        Usuario usuario = new Usuario(nome,senha,email);
        ConfiguracaoFirebase.salvarUsuarioFirebase(usuario);
        MainActivity.usuarioLogado = usuario;
        return true;

    }

    public static Usuario pegarUsuario(String email){
        for (int i = 0; i < ConfiguracaoFirebase.usuarios.size(); i++) {
            if(ConfiguracaoFirebase.usuarios.get(i).getEmail().equals(email)){
                return ConfiguracaoFirebase.usuarios.get(i);
            }
        }
        return null;
    }

    public static void addTag(String nome) {
        Tag tag = new Tag(nome);
        ConfiguracaoFirebase.salvarTag(tag);
    }
    public static boolean adicionarColaborador(Usuario usuario, Evento evento){
        ConfiguracaoFirebase.salvarColaboradores(usuario,evento);
        return true;
    }

}
