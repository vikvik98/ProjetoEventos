package com.example.vinic.projetoeventos.app;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.holder.EventosAdapter;
import com.example.vinic.projetoeventos.holder.FragmentEventos;
import com.example.vinic.projetoeventos.holder.FragmentInscricao;
import com.example.vinic.projetoeventos.holder.FragmentMeusEventos;
import com.example.vinic.projetoeventos.holder.ViewPageAdapter;
import com.example.vinic.projetoeventos.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private ViewPageAdapter adapter;
    public static Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void novoEvento(View view) {
        startActivity(new Intent(this,CadastrarEventoActivity.class));

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentMeusEventos(), "Meus eventos");
        adapter.addFragment(new FragmentEventos(), "Eventos");
        adapter.addFragment(new FragmentInscricao(), "Inscrições");
        viewPager.setAdapter(adapter);
    }

}
