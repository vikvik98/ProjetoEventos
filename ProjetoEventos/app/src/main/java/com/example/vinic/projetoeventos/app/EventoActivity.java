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
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.holder.ListaAtividadesRVAdapter;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventoActivity extends AppCompatActivity {

    private RecyclerView rvAtividades;
    private TextView tvEventoNome;
    private TextView tvEventoData;
    private TextView tvEventoLocal;
    private Evento evento;
    private List<Atividade> atividades;
    private ListaAtividadesRVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        setupViews();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        for (int i = 0; i < ConfiguracaoFirebase.eventosList.size(); i++) {
            if (ConfiguracaoFirebase.eventosList.get(i).getId().equals(id)) {
                evento = ConfiguracaoFirebase.eventosList.get(i);
                break;
            }
        }

        tvEventoNome.setText(evento.getNome());
        tvEventoLocal.setText(evento.getLocal());

        try {
            tvEventoData.setText(new SimpleDateFormat("dd/MM/yy").parse(evento.getDataInicial().toString()).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        atividades = evento.getAtividades();


    }

    private void setupViews() {
        rvAtividades = findViewById(R.id.rv_atividades);
        tvEventoNome = findViewById((R.id.lista_atividade_titulo));
        tvEventoData = findViewById(R.id.lista_atividade_data);
        tvEventoLocal = findViewById(R.id.lista_atividade_local);
        atividades = new ArrayList<>();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        reloadData();

    }

    private void reloadData() {

        adapter = new ListaAtividadesRVAdapter(this,atividades);
        rvAtividades.setAdapter(adapter);
        rvAtividades.setLayoutManager(new LinearLayoutManager(EventoActivity.this));

    }

    public void adicionarAtividade(View view) {
        Intent intent = new Intent(this, CadastrarAtividadeActivity.class).putExtra("id",evento.getId());
        startActivity(intent);
    }

    public void gerarCupom(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.alert_cupom, null);

        builder.setTitle("Gerar cumpons: ");
        builder.setView(inflate)

                .setPositiveButton("Concluir", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(EventoActivity.this, "Deu bom", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create();
        builder.show();
    }
}
