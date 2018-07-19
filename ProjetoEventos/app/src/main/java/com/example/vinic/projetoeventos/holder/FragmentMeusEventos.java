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
    private EventoCases eventoCases;
    private static View view;
    private List<Evento> meusEventos;
    private List<Evento> eventosList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meus_eventos, container, false);

        setupViews();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }

    private void reloadData() {

        adapter = new EventosAdapter(getContext(), pegarMeusEventos(MainActivity.usuarioLogado));
        rvEventos.setAdapter(adapter);
        rvEventos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEventos.setHasFixedSize(true);
    }

    private List<Evento> pegarMeusEventos(Usuario usuarioLogado){
        eventosList = eventoCases.pegarEventosNoFirebase();
        //Log.d("certo","" + eventosList.size());
        for (int i = 0; i < eventosList.size(); i++){
            //Log.d("certo",eventosList.get(i).getKeyCriador() + "id: " + usuarioLogado.getId());
            if (eventosList.get(i).getKeyCriador().equals(usuarioLogado.getId())){
                meusEventos.add(eventosList.get(i));
                //Log.d("certo", "Adicionou");
            }
        }
        //Log.d("certo", "nao pegou ninguem");
        return meusEventos;
    }

    private void setupViews() {
        rvEventos = view.findViewById(R.id.rv_eventos);
        eventosList = new ArrayList<>();
        meusEventos = new ArrayList<>();
        eventoCases = new EventoCases();
    }

}
