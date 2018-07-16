package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.holder.EventosAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvEventos;
    private EventosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //reloadData();
    }

//    private void reloadData() {
//
//        adapter = new EventosAdapter(this, LoginActivity.usuario.getEventos());
//        rvEventos.setAdapter(adapter);
//        rvEventos.setLayoutManager(new LinearLayoutManager(this));
//        rvEventos.setHasFixedSize(true);
//
//    }


    private void setupViews () {

        //rvEventos = findViewById(R.id.rv_eventos);

    }

    public void novoEvento(View view) {
        startActivity(new Intent(this,CadastrarEventoActivity.class));


    }

    //reclamação, se eu conseguir resolver, ai está a oportunidade
}
