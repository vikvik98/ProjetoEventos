package com.example.vinic.projetoeventos.app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.controller.EventoController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.PendingIntent.getActivity;

public class CadastrarAtividadeActivity extends AppCompatActivity {

    private EditText nome;
    private TextView txtDataAtividade;
    private EditText tipo;
    private EditText descricao;
    private EditText valor;
    private Calendar dataAtividade;
    private EditText horaInicio;
    private EditText horaFinal;
    private String idEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_atividade);

        idEvento = getIntent().getStringExtra("id");

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public void pegarData(View view) {
        int ano = dataAtividade.get(Calendar.YEAR);
        int mes = dataAtividade.get(Calendar.MONTH);
        int dia = dataAtividade.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, i, i1, i2) -> {
                    dataAtividade.set(i, i1, i2);
                    txtDataAtividade.setText(new SimpleDateFormat("dd/MM/yyyy")
                            .format(dataAtividade.getTime()));
                }, ano, mes, dia);

        /** Definir data mínima a ser escolhida sendo a data atual */
        datePickerDialog.getDatePicker().setMinDate(dataAtividade.getTimeInMillis());
        datePickerDialog.show();

    }

    private void setupViews(){
        nome = findViewById(R.id.nome_atividade);
        txtDataAtividade = findViewById(R.id.data_evento);
        tipo = findViewById(R.id.add_tipo_atividade);
        descricao = findViewById(R.id.descricao_atividade);
        valor = findViewById(R.id.valor_atividade);
        dataAtividade = Calendar.getInstance();
        horaInicio = findViewById(R.id.add_hora_inicio);
        horaFinal = findViewById(R.id.add_hora_final);
    }


    public void adicionarEvento(View view) {
        if(camposVazios(nome, txtDataAtividade, descricao, valor, horaInicio, horaFinal)){
            Toast.makeText(this, R.string.preencher_campos, Toast.LENGTH_SHORT).show();
        }else{
            Log.d("msg", idEvento);
            for(int i = 0; i < EventoCases.eventosList.size(); i++){
                Log.d("msg", "" + EventoCases.eventosList.get(i));
                if(EventoCases.eventosList.get(i).getId().equals(idEvento)){
                    EventoController.cadastrarAtividade(EventoCases.eventosList.get(i), nome, tipo, horaInicio, horaFinal, valor);
                    break;
                }
            }
        }
    }

    public boolean camposVazios(EditText nome, TextView data, EditText descricao, EditText valor, EditText horaInicio, EditText horaFinal){
        if (nome.getText().toString().trim().equals("")
                || data.getText().toString().trim().equals("DD/MM/YYYY")
                || descricao.getText().toString().trim().equals("")
                || valor.getText().toString().trim().equals("")
                || horaInicio.getText().toString().trim().equals("")
                || horaFinal.getText().toString().trim().equals("")){
            return true;
        }else return false;
    }
}
