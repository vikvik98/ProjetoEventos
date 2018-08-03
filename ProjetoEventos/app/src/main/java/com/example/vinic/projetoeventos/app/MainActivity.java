package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.holder.EventosAdapter;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvEventos;
    private EventosAdapter adapter;
    private List<Evento> meusEventos = new ArrayList<>();
    public static Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawer(toolbar);

        rvEventos = findViewById(R.id.rv_eventos);
        reloadData(pegarEventos(MainActivity.usuarioLogado));
    }

    @Override
    protected void onResume() {
        super.onResume();

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

    private List<Evento> pegarEventos(Usuario usuarioLogado){
        this.meusEventos.clear();
        for (int i = 0; i < EventoCases.eventosList.size(); i++){
            if (!EventoCases.eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())){
                meusEventos.add(EventoCases.eventosList.get(i));

            }
        }
        return meusEventos;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_eventos){
            this.meusEventos.clear();
            for (int i = 0; i < EventoCases.eventosList.size(); i++) {
                if (!EventoCases.eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())) {
                    meusEventos.add(EventoCases.eventosList.get(i));
                }
            }
            reloadData(meusEventos);

        } else if (id == R.id.nav_meus_eventos) {
            this.meusEventos.clear();
            for (int i = 0; i < EventoCases.eventosList.size(); i++) {
                if (EventoCases.eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())) {
                    meusEventos.add(EventoCases.eventosList.get(i));
                }
            }
            reloadData(meusEventos);

            //TODO: Fazer que fab seja mostrado em meus eventos activytty

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
}
