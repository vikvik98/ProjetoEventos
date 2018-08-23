package com.example.vinic.projetoeventos.app;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.holder.EventosAdapter;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvEventos;
    private EventosAdapter adapter;
    private List<Evento> eventos = new ArrayList<>();
    private FloatingActionButton addEvento;
    public static Usuario usuarioLogado;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        shared = getSharedPreferences("event", MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawer(toolbar);
        setupVIews();
    }

    private void setupVIews() {
        addEvento = findViewById(R.id.add_evento_fab);
        rvEventos = findViewById(R.id.rv_eventos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean first = shared.getBoolean("first", false);
        String volta = shared.getString("volta", "nenhum");

        if (first){

            reloadData(ConfiguracaoFirebase.pegarEventos(usuarioLogado));
            addEvento.setVisibility(View.GONE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putBoolean("first", false);
            editor.commit();
            editor.apply();
        }else if (volta.equals("inscricao")){
            reloadData(pegarEventos(usuarioLogado));
        }else if (volta.equals("evento")){
            reloadData(pegarMeusEventos(usuarioLogado));
        }

    }




    private List<Evento> pegarMeusEventos(Usuario usuarioLogado) {
        this.eventos.clear();
        for (int i = 0; i < ConfiguracaoFirebase.eventosList.size(); i++){
            if (ConfiguracaoFirebase.eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())){
                eventos.add(ConfiguracaoFirebase.eventosList.get(i));

            }
        }
        return eventos;
    }


    private void setupDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void reloadData(List<Evento> eventoList) {

        adapter = new EventosAdapter(this, eventoList);
        rvEventos.setAdapter(adapter);
        rvEventos.setLayoutManager(new LinearLayoutManager(this));
        rvEventos.setHasFixedSize(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_eventos){
            addEvento.setVisibility(View.GONE);
            reloadData(ConfiguracaoFirebase.pegarEventos(usuarioLogado));


        } else if (id == R.id.nav_meus_eventos) {

            addEvento.setVisibility(View.VISIBLE);
            reloadData(ConfiguracaoFirebase.pegarMeusEventos(usuarioLogado));

        }else if (id == R.id.nav_eventos_colaborados) {

            addEvento.setVisibility(View.GONE);
            reloadData(ConfiguracaoFirebase.pegarColaboracoes(MainActivity.usuarioLogado));


        } else if (id == R.id.nav_eventos_inscritos) {
            //robherty


        } else if (id == R.id.nav_editar_conta) {
            //robherty

        } else if (id == R.id.nav_sair) {
            usuarioLogado = null;
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void adicionandoEvento(View view) {
        startActivityForResult(new Intent(this, CadastrarEventoActivity.class), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                addEvento.setVisibility(View.VISIBLE);
                reloadData(pegarMeusEventos(usuarioLogado));
            }
        }else{
            if (resultCode == RESULT_OK){
                addEvento.setVisibility(View.GONE);
                reloadData(ConfiguracaoFirebase.pegarEventos(usuarioLogado));
            }
        }
    }
}
