package com.example.vinic.projetoeventos.holder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.app.LoginActivity;
import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class FragmentMeusEventos extends android.support.v4.app.Fragment{

    private RecyclerView rvEventos;
    private EventosAdapter adapter;
    private static View view;
    private List<Evento> meusEventos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meus_eventos, container, false);
        setupViews();
        reloadData();
        return view;
    }

    private void reloadData() {


        adapter = new EventosAdapter(getContext(), pegarMeusEventos(MainActivity.usuarioLogado));
        rvEventos.setAdapter(adapter);
        rvEventos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEventos.setHasFixedSize(true);
    }

    private List<Evento> pegarMeusEventos(Usuario usuarioLogado){
        this.meusEventos.clear();
        for (int i = 0; i < EventoCases.eventosList.size(); i++){
            if (EventoCases.eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())){
                meusEventos.add(EventoCases.eventosList.get(i));
                Log.d("certo", "" + meusEventos.size());
            }
        }
        Log.d("certo", "" + meusEventos.size());
        return meusEventos;
    }

    private void setupViews() {
        rvEventos = view.findViewById(R.id.rv_eventos);
        meusEventos = new ArrayList<>();
    }

}
