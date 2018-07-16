package com.example.vinic.projetoeventos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.holder.EventosAdapter;

public class InscricaoActivity extends AppCompatActivity {

    private RecyclerView rvInscricao;
    private EventosAdapter adapter;
    private CardView cardEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

    }

    private void reloadData() {

        // adapter = new EventosAdapter(this, livros);
        rvInscricao.setAdapter(adapter);
        rvInscricao.setLayoutManager(new LinearLayoutManager(this));
        rvInscricao.setHasFixedSize(true);

    }


    private void setupViews () {

        // rv = findViewById(R.id.rv_livros);
        cardEvento = findViewById(R.id.card_evento);
    }

}
