package com.example.vinic.projetoeventos.cases;

import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Cupom;
import com.example.vinic.projetoeventos.model.Evento;

import java.util.Date;

public class CupomCases {

    public static void cadastrarCupom(Evento evento, Date dataInicio, Date dataFinal, double percentual){

        Cupom cupom = new Cupom(dataInicio,dataFinal,percentual);
        ConfiguracaoFirebase.salvarCupomFirebase(evento,cupom);

    }

}
