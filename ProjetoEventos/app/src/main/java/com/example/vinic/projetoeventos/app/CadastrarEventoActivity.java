package com.example.vinic.projetoeventos.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.controller.UsuarioController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastrarEventoActivity extends AppCompatActivity {

    private EditText editTextNomeEvento;
    private TextView textViewData;
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
        if(!(editTextNomeEvento.getText().toString().equals("")) &&
                !(editTextLocal).getText().toString().equals("") &&
                !(editTextTipoEvento.getText().toString().equals(""))){

            UsuarioController.cadastrarEvento(editTextNomeEvento,textViewData,editTextLocal,editTextTipoEvento);
            finish();
        }else{
            Toast.makeText(this, "Algum campo está vazio!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViews() {
        editTextNomeEvento = findViewById(R.id.add_nome_evento);
        textViewData = findViewById(R.id.add_data_evento);
        editTextLocal = findViewById(R.id.add_local_evento);
        editTextTipoEvento = findViewById(R.id.add_tipo_evento);

        dataEvento = Calendar.getInstance();
    }

    public void pegarDataEvento(View view) {
        int ano = dataEvento.get(Calendar.YEAR);
        int mes = dataEvento.get(Calendar.MONTH);
        int dia = dataEvento.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, i, i1, i2) -> {
                    dataEvento.set(i, i1, i2);
                    textViewData.setText(new SimpleDateFormat("dd/MM/yyyy")
                            .format(dataEvento.getTime()));
                }, ano, mes, dia);

        /** Definir data mínima a ser escolhida sendo a data atual */
        datePickerDialog.getDatePicker().setMinDate(dataEvento.getTimeInMillis());
        datePickerDialog.show();
    }

}
