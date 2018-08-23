package com.example.vinic.projetoeventos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.model.Tag;

public class AdicionarTagInteresseAcitivity extends AppCompatActivity {
    private EditText tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_tag_interesse_acitivity);

        tag = findViewById(R.id.tag);

    }


    public void alterarUsuario(View view) {
        Toast.makeText(this, "acho q ia dar certo?", Toast.LENGTH_SHORT).show();
    }

    public void adcionarTag(View view) {
        UsuarioCases.addTag(tag.getText().toString());
        Toast.makeText(this, "Adicionado com sucesso", Toast.LENGTH_SHORT).show();
        tag.setText("");
    }
}
