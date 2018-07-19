package com.example.vinic.projetoeventos.app;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.holder.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.PendingIntent.getActivity;

public class CadastrarAtividadeActivity extends AppCompatActivity {

    private EditText nome;
    private TextView dataEvento;
    private RadioGroup rgTipo;
    private EditText descricao;
    private EditText valor;
    private Calendar dataAtividade;
    private Calendar horaAtividadeIncio;
    private TextView horaAtividade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_atividade);


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
                    dataEvento.setText(new SimpleDateFormat("dd/MM/yyyy")
                            .format(dataAtividade.getTime()));
                }, ano, mes, dia);

        /** Definir data mínima a ser escolhida sendo a data atual */
        datePickerDialog.getDatePicker().setMinDate(dataAtividade.getTimeInMillis());
        datePickerDialog.show();

    }

    private void setupViews(){
        nome = findViewById(R.id.nome_atividade);
        dataEvento = findViewById(R.id.data_evento);
        rgTipo = findViewById(R.id.group_tipo_atividade);
        descricao = findViewById(R.id.descricao_atividade);
        valor = findViewById(R.id.valor_atividade);
        dataAtividade = Calendar.getInstance();
        horaAtividade = findViewById(R.id.add_hora_inicio);
    }

    public void pegarHora(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}
