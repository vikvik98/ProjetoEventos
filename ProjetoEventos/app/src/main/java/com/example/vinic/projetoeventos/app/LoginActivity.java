package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.EventoCases;
import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.controller.UsuarioController;



public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();
        UsuarioCases.pegarUsuariosNoFirebase();
        EventoCases.pegarEventosNoFirebase();
    }


    public void entrar(View view){

        boolean existe = false;

        for (int i = 0; i < UsuarioCases.usuarios.size(); i++) {
            existe = isExiste(existe, i);

            if (existe){
                realizarLogin();
            }else{
                Toast.makeText(this, "Usuario não encontrado!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void realizarLogin() {
        MainActivity.usuarioLogado = UsuarioController.logarUsuario(editTextEmail);
        Toast.makeText(this, "Usuario logado com sucesso!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean isExiste(boolean existe, int i) {
        if(UsuarioCases.usuarios.get(i).getEmail().equals(editTextEmail.getText().toString())){
            if(UsuarioCases.usuarios.get(i).getSenha().equals(editTextSenha.getText().toString())){
                existe = true;
            }
        }
        return existe;
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }


    private void setupViews() {
        editTextEmail = findViewById(R.id.id_email);
        editTextSenha = findViewById(R.id.id_senha);

    }

}