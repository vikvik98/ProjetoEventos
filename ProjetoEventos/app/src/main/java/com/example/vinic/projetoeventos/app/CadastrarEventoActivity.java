package com.example.vinic.projetoeventos.app;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastrarEventoActivity extends AppCompatActivity {

    private EditText editTextNomeEvento;
    private TextView textViewDataInicio;
    private TextView textViewDataFinal;
    private EditText editTextQuantPessoas;
    private EditText editTextLocal;
    private EditText editTextTipoEvento;
    private Calendar dataEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        setupViews();
    }

    public void adicionarEvento(View view) {
        if (camposVazios(editTextNomeEvento,editTextTipoEvento,editTextLocal,textViewDataInicio,textViewDataFinal,editTextQuantPessoas)){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

            String nomeEvento = editTextNomeEvento.getText().toString();
            String tipoEvento = editTextTipoEvento.getText().toString();
            String localEvento = editTextLocal.getText().toString();
            int quantPessoas = Integer.parseInt(editTextQuantPessoas.getText().toString());

            try {
                Date dataEventoInicio = format.parse(textViewDataInicio.getText().toString());
                Date dataEventoFinal = format.parse(textViewDataFinal.getText().toString());
                EventoCases.cadastrarEvento(nomeEvento,tipoEvento,localEvento,dataEventoInicio,dataEventoFinal,quantPessoas, MainActivity.usuarioLogado.getId());
                finish();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean camposVazios(EditText editTextNomeEvento,EditText editTextLocal,
                                 EditText editTextTipoEvento, TextView textViewDataInicio,
                                 TextView textViewDataFim, EditText editTextQuantPessoas){
        if(editTextNomeEvento.getText().toString().trim().equals("")
                || editTextLocal.getText().toString().trim().equals("")
                || editTextTipoEvento.getText().toString().trim().equals("")
                || textViewDataInicio.getText().toString().equals("DD-MM-AAAA")
                || textViewDataFim.getText().toString().equals("DD-MM-AAAA")
                || editTextQuantPessoas.getText().toString().trim().equals("")) {
            return false;
        }
        return true;
    }

    private void setupViews() {
        editTextNomeEvento = findViewById(R.id.add_nome_evento);
        textViewDataInicio = findViewById(R.id.add_data_inicio_evento);
        textViewDataFinal = findViewById(R.id.add_data_termino_evento);
        editTextLocal = findViewById(R.id.add_local_evento);
        editTextTipoEvento = findViewById(R.id.add_tipo_evento);
        editTextQuantPessoas = findViewById(R.id.add_quant_pessoas_evento);
        dataEvento = Calendar.getInstance();
    }


    public void pegarData(View view) {

        int ano = dataEvento.get(Calendar.YEAR);
        int mes = dataEvento.get(Calendar.MONTH);
        int dia = dataEvento.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, i, i1, i2) -> {
                    dataEvento.set(i, i1, i2);
                    if (R.id.add_data_inicio_evento == view.getId()){
                        textViewDataInicio.setText(new SimpleDateFormat("dd/MM/yyyy")
                                .format(dataEvento.getTime()));
                    }else{
                        textViewDataFinal.setText(new SimpleDateFormat("dd/MM/yyyy")
                                .format(dataEvento.getTime()));
                    }
                }, ano, mes, dia);

        /** Definir data mínima a ser escolhida sendo a data atual */
        if(R.id.add_data_inicio_evento == view.getId()){
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        }
        else{
            datePickerDialog.getDatePicker().setMinDate(dataEvento.getTimeInMillis());
        }

        datePickerDialog.show();
    }


}
