package com.example.vinic.projetoeventos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.holder.ListaAtividadesRVAdapter;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;

import java.util.List;

public class InscricaoActivity extends AppCompatActivity {

    RecyclerView rvAtividades;
    TextView tvEventoNome;
    TextView tvEventoData;
    TextView tvEventoLocal;
    Evento evento;
    List<Atividade> atividades;
    ListaAtividadesRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);
    }
}
