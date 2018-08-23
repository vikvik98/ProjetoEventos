package com.example.vinic.projetoeventos.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
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
    int valorAtividades;

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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View viewDialog = getLayoutInflater().inflate(R.layout.adicionar_cupom,null);

        sb = new StringBuffer();
        valorAtividades = 0;
        for(Atividade atividade : adapter.atividadesChecadas){
            valorAtividades += atividade.getValor();
        }

        if(adapter.atividadesChecadas.size()>0){
            builder.setView(viewDialog)
                    .setTitle("Total: " + String.valueOf(valorAtividades) +"\nAplicar desconto(opcional)")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            EditText etCupom = viewDialog.findViewById(R.id.et_cupom);
                            String cupom = etCupom.getText().toString();

                            for(int j = 0; j < evento.getCupons().size(); j++){
                                if(cupom.equals(evento.getCupons().get(j).getCodigo())){
                                    for(Atividade atividade : adapter.atividadesChecadas){
                                        //Atividade atividadeNova = atividade;
                                        float desconto = (float) (atividade.getValor() * evento.getCupons().get(j).getPorcentagem());
                                        atividade.setValor(atividade.getValor() - desconto);
                                        evento.getCupons().get(j).setQuant(evento.getCupons().get(j).getQuant()-1);
                                        InscricaoCases.inserirAtividadeNaInscricao(MainActivity.usuarioLogado.getId(), evento.getId(), atividade);
                                        Toast.makeText(InscricaoActivity.this, "Cupom adicionado", Toast.LENGTH_SHORT).show();
                                        //finish();

                                        }
                                    for(Atividade atividade : adapter.atividadesChecadas) {
                                        InscricaoCases.inserirAtividadeNaInscricao(MainActivity.usuarioLogado.getId(), evento.getId(), atividade);
                                    }

                                }else{
                                    for(Atividade atividade : adapter.atividadesChecadas){
                                        InscricaoCases.inserirAtividadeNaInscricao(MainActivity.usuarioLogado.getId(), evento.getId(), atividade);
                                        Toast.makeText(InscricaoActivity.this, "Valor sem cupom", Toast.LENGTH_SHORT).show();
                                        finish();

                                    }

                                }
                            }
                        //finish();
                        }
                    })
                    .setNegativeButton("Cancelar",null)
                    .show();
            Toast.makeText(InscricaoActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(InscricaoActivity.this, "Selecione pelo menos uma atividade.", Toast.LENGTH_SHORT).show();
        }


    }

    public void selecionarTudo(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View viewDialog = getLayoutInflater().inflate(R.layout.adicionar_cupom,null);


       builder.setView(viewDialog)
               .setTitle("Inscrever-se em tudo?")
               .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       EditText etCupom = viewDialog.findViewById(R.id.et_cupom);
                       String cupom = etCupom.getText().toString();
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
