package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.controller.UsuarioController;

public class CadastrarEventoActivity extends AppCompatActivity {

    private EditText editTextNomeEvento;
    private EditText editTextDataEvento;
    private EditText editTextTipoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        setupViews();
    }

    public void adicionarEvento(View view) {
        startActivity(new Intent(this, CadastrarAtividadeActivity.class));
        if(!(editTextNomeEvento == null) && !(editTextDataEvento == null) && !(editTextTipoEvento == null)){
            UsuarioController.cadastrarEvento(editTextNomeEvento,editTextDataEvento,editTextTipoEvento);
        }
    }

    private void setupViews() {
        editTextNomeEvento = findViewById(R.id.add_nome_evento);
        editTextDataEvento = findViewById(R.id.add_data_evento);
        editTextTipoEvento = findViewById(R.id.add_tipo_evento);
    }

}
