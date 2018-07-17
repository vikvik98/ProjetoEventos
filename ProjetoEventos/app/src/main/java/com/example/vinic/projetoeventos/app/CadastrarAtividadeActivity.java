package com.example.vinic.projetoeventos.app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.vinic.projetoeventos.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastrarAtividadeActivity extends AppCompatActivity {

    private EditText nome;
    private TextView dataEvento;
    private RadioGroup rgTipo;
    private EditText descricao;
    private EditText valor;
    private Calendar myCalendar;


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
        int ano = myCalendar.get(Calendar.YEAR);
        int mes = myCalendar.get(Calendar.MONTH);
        int dia = myCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, i, i1, i2) -> {
                    myCalendar.set(i, i1, i2);
                    dataEvento.setText(new SimpleDateFormat("dd/MM/yyyy")
                            .format(myCalendar.getTime()));
                }, ano, mes, dia);

        /** Definir data mínima a ser escolhida sendo a data atual */
        datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
        datePickerDialog.show();

    }

    private void setupViews(){
        nome = findViewById(R.id.nome_atividade);
        dataEvento = findViewById(R.id.data_evento);
        rgTipo = findViewById(R.id.group_tipo_atividade);
        descricao = findViewById(R.id.descricao_atividade);
        valor = findViewById(R.id.valor_atividade);
        myCalendar = Calendar.getInstance();
    }
}
