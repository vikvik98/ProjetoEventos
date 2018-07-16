package com.example.vinic.projetoeventos.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.controller.UsuarioController;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setupViews();
    }

    public void efetuarCadastro(View view) {
        UsuarioController.cadastrarUsuario(editTextNome,editTextSenha,editTextEmail);
        Toast.makeText(this, "Deu bom rapa!", Toast.LENGTH_SHORT).show();
    }

    private void setupViews() {
        editTextNome = findViewById(R.id.nome_cadastro);
        editTextEmail = findViewById(R.id.email_cadastro);
        editTextSenha = findViewById(R.id.senha_cadastro);
    }
}
