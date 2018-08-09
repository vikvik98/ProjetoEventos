package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.holder.ListaAtividadesRVAdapter;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EventoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_evento);

        rvAtividades = (RecyclerView) findViewById(R.id.rv_atividades);
        tvEventoNome = (TextView) findViewById((R.id.lista_atividade_titulo));
        tvEventoData = (TextView) findViewById(R.id.lista_atividade_data);
        tvEventoLocal = (TextView) findViewById(R.id.lista_atividade_local);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");


        for (int i = 0; i <= EventoCases.eventosList.size()-1; i++) {
            if (EventoCases.eventosList.get(i).getId().equals(id)) {
                evento = EventoCases.eventosList.get(i);
            }
        }

        tvEventoNome.setText(evento.getNome());
        tvEventoLocal.setText(evento.getLocal());
        try {
            tvEventoData.setText(new SimpleDateFormat("dd/MM/yy").parse(evento.getDataInicial().toString()).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        atividades = evento.getAtividades();

    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadData();
    }

    private void reloadData() {

        adapter = new ListaAtividadesRVAdapter(this,atividades);
        rvAtividades.setAdapter(adapter);
        rvAtividades.setLayoutManager(new LinearLayoutManager(EventoActivity.this));

    }

    public void adicionarAtividade(View view) {
        Intent intent = new Intent(this, CadastrarAtividadeActivity.class).putExtra("id",evento.getId());
        startActivity(intent);
    }
}
