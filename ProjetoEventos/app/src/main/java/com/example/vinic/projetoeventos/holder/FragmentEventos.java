package com.example.vinic.projetoeventos.holder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.app.LoginActivity;
import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.cases.UsuarioCases;


public class FragmentEventos extends android.support.v4.app.Fragment{

    private RecyclerView rvEventos;
    private EventosAdapter adapter;
    private static View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_eventos, container, false);

        setupViews();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();

    }

    private void reloadData() {

        adapter = new EventosAdapter(getActivity(), MainActivity.usuarioLogado.getEventos());
        rvEventos.setAdapter(adapter);
        rvEventos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvEventos.setHasFixedSize(true);

    }


    private void setupViews() {
        rvEventos = view.findViewById(R.id.rv_eventos);
    }

}
