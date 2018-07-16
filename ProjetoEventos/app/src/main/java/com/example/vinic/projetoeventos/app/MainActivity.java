package com.example.vinic.projetoeventos.app;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.holder.EventosAdapter;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvEventos;
    private EventosAdapter adapter;
    private CardView cardEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

    }

    private void reloadData() {

       // adapter = new ListaLivrosAdapter(this, livros, boxLivros);
        rvEventos.setAdapter(adapter);
        rvEventos.setLayoutManager(new LinearLayoutManager(this));
        rvEventos.setHasFixedSize(true);

    }

    private void setupViews () {

       // rv = findViewById(R.id.rv_livros);
        cardEvento = findViewById(R.id.card_evento);



    }

    //reclamação, se eu conseguir resolver, ai está a oportunidade
}
