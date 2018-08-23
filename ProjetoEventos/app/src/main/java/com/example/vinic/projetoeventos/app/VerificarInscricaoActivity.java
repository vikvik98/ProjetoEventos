package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.holder.EventosAdapter;
import com.example.vinic.projetoeventos.holder.InscricoesAdapter;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Inscricao;

import java.util.List;

public class VerificarInscricaoActivity extends AppCompatActivity {
    private RecyclerView rvVerificarAtividades;
    private InscricoesAdapter adapter;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_inscricao);

        Intent i = getIntent();
        key = i.getStringExtra("id");

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadData(ConfiguracaoFirebase.pegarInscricoes(key));
    }

    private void setupViews(){
        rvVerificarAtividades = findViewById(R.id.rv_verificar_atividades);
    }

    private void reloadData(List<Inscricao> inscricaoList) {

        adapter = new InscricoesAdapter(this, inscricaoList);
        rvVerificarAtividades.setAdapter(adapter);
        rvVerificarAtividades.setLayoutManager(new LinearLayoutManager(this));
        rvVerificarAtividades.setHasFixedSize(true);

    }
}
