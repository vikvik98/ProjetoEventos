package com.example.vinic.projetoeventos.controller;

import android.widget.EditText;
import android.widget.TextView;

import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventoController {

    public static void cadastrarEvento(EditText nome, EditText tipo, EditText local, TextView dataInicio, TextView dataFinal, EditText quant){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        String nomeEvento = nome.getText().toString();
        String tipoEvento = tipo.getText().toString();
        String localEvento = local.getText().toString();
        int quantPessoas = Integer.parseInt(quant.getText().toString());

        try {
            Date dataEventoInicio = format.parse(dataInicio.getText().toString());
            Date dataEventoFinal = format.parse(dataFinal.getText().toString());
            EventoCases.cadastrarEvento(nomeEvento,tipoEvento,localEvento,dataEventoInicio,dataEventoFinal,quantPessoas, MainActivity.usuarioLogado.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static void cadastrarAtividade(Evento evento, EditText nome, EditText tipo, EditText horaInicio,EditText horaTermino, EditText valor){
        String nomeAtividade = nome.getText().toString();
        String tipoAtividade = tipo.getText().toString();
        String horaInicioAtividade = horaInicio.getText().toString();
        String horaTerminoAtivade = horaTermino.getText().toString();
        double valorAtividade = Double.parseDouble(valor.getText().toString());
        EventoCases.cadastrarAtividade(evento,nomeAtividade,tipoAtividade,horaInicioAtividade,horaTerminoAtivade,valorAtividade);


    }

}
