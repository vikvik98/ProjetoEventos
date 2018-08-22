package com.example.vinic.projetoeventos.app;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.CupomCases;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.holder.ListaAtividadesRVAdapter;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Cupom;
import com.example.vinic.projetoeventos.model.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventoActivity extends AppCompatActivity {

    private RecyclerView rvAtividades;
    private TextView tvEventoNome;
    private TextView tvEventoData;
    private TextView tvEventoLocal;
    private Evento evento;
    private List<Atividade> atividades;
    private ListaAtividadesRVAdapter adapter;
    private Spinner spinner;
    private double porcentagem;
    private Calendar dataCupom;
    private TextView tvDataTerminoCupom;


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
        tvDataTerminoCupom = findViewById(R.id.data_termino_cupom);
        atividades = new ArrayList<>();
        dataCupom = Calendar.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cupom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.alert_cupom, null);

        EditText quantidadeCupons = inflate.findViewById(R.id.cupom_quant);
        spinner = inflate.findViewById(R.id.spinnerCupom);
        tvDataTerminoCupom = inflate.findViewById(R.id.data_termino_cupom);


        builder.setTitle("Gerar cumpons: ");
        builder.setView(inflate)

                .setPositiveButton("Concluir", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (spinner.getSelectedItemPosition() == 0) {
                            porcentagem = 0.5;
                        } else if (spinner.getSelectedItemPosition() == 1) {
                            porcentagem = 0.25;
                        } else {
                            porcentagem = 0.5;
                        }

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                        Date hoje = new Date();
                        format.format(hoje);

                        try {
                            Date dataTerminoCupom = format.parse(tvDataTerminoCupom.getText().toString());
                            Date dataInicioCupom = format.parse(format.format(hoje));
                            for (int j = 0; j < Integer.parseInt(quantidadeCupons.getText().toString()); j++){
                                CupomCases.cadastrarCupom(evento,dataInicioCupom,dataTerminoCupom,porcentagem);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create();
        builder.show();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    public void pegarData(View view) {
        int ano = dataCupom.get(Calendar.YEAR);
        int mes = dataCupom.get(Calendar.MONTH);
        int dia = dataCupom.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, i, i1, i2) -> {
                    dataCupom.set(i, i1, i2);
                    tvDataTerminoCupom.setText(new SimpleDateFormat("dd/MM/yyyy")
                            .format(dataCupom.getTime()));
                }, ano, mes, dia);

        datePickerDialog.getDatePicker().setMinDate(evento.getDataInicial().getTime());
        datePickerDialog.getDatePicker().setMaxDate(evento.getDataFinal().getTime());
        datePickerDialog.show();

    }
}
