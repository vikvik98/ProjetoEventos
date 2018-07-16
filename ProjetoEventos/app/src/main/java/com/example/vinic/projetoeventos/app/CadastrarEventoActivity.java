package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vinic.projetoeventos.R;

public class CadastrarEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
    }

    public void adicionarEvento(View view) {
        startActivity(new Intent(this, CadastrarAtividadeActivity.class));
    }
}
