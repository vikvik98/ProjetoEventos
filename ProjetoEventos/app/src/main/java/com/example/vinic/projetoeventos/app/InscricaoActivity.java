package com.example.vinic.projetoeventos.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.InscricaoCases;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.holder.ListaAtividadesRVAdapter;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Inscricao;

import java.util.ArrayList;
import java.util.List;

public class InscricaoActivity extends AppCompatActivity {

    RecyclerView rvInscricao;
    Evento evento;
    List<Atividade> atividades;
    ListaAtividadesRVAdapter adapter;
    StringBuffer sb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);
        setupViews();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        for (int i = 0; i < ConfiguracaoFirebase.eventosList.size(); i++) {
            if (ConfiguracaoFirebase.eventosList.get(i).getId().equals(id)) {
                evento = ConfiguracaoFirebase.eventosList.get(i);
                break;
            }
        }

        atividades = evento.getAtividades();


        //getActionBar().setTitle(evento.getNome());
    }

    @Override
    protected void onResume() {
        super.onResume();
    reloadData();
    }

    private void setupViews() {
        rvInscricao = findViewById(R.id.rv_inscricao);
        atividades = new ArrayList<>();

    }

    private void reloadData() {

        adapter = new ListaAtividadesRVAdapter(this,atividades);
        rvInscricao.setAdapter(adapter);
        rvInscricao.setLayoutManager(new LinearLayoutManager(InscricaoActivity.this));

    }

    public void confirmarInscricao(View view) {

        sb = new StringBuffer();

        for(Atividade atividade : adapter.atividadesChecadas){
            InscricaoCases.inserirAtividadeNaInscricao(MainActivity.usuarioLogado.getId(), evento.getId(), atividade);
            sb.append(atividade.getNome());
            sb.append("\n");


        }

        if(adapter.atividadesChecadas.size()>0){
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Selecione pelo menos uma atividade.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void selecionarTudo(View view) {
        for(Atividade atividade : adapter.atividadesChecadas){

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

       builder.setTitle("Inscrever-se em tudo?")
               .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       for (int j = 0; j < atividades.size(); j++) {
                           Toast.makeText(InscricaoActivity.this, "" + j, Toast.LENGTH_SHORT).show();
                           InscricaoCases.inserirAtividadeNaInscricao(MainActivity.usuarioLogado.getId(), evento.getId(), atividades.get(j));

                       }finish();
                   }
               })
               .setNegativeButton("Cancelar",null)
               .show();

    }
}
