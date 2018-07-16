package com.example.vinic.projetoeventos.controller;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UsuarioController {

    public UsuarioController() {

    }

    public static void cadastrarUsuario(EditText nome, EditText senha, EditText email){
        String nomeUsuario = nome.getText().toString();
        String senhaUsuario = senha.getText().toString();
        String emailUsuario = email.getText().toString();

        UsuarioCases.cadastrarUsuario(nomeUsuario,senhaUsuario,emailUsuario);

    }


    public static Usuario logarUsuario(EditText email){
        String emailUsuario = email.getText().toString();
        return UsuarioCases.logarUsuario(emailUsuario);
    }


    public static void cadastrarEvento(EditText nome, EditText data, EditText local){
        String nomeEvento = nome.getText().toString();
        String dataEvento = data.getText().toString();
        String localEvento = local.getText().toString();
        UsuarioCases.cadastrarEvento(nomeEvento,dataEvento,localEvento);
    }



}
