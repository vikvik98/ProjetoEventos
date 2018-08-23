package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.UsuarioCases;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();
        ConfiguracaoFirebase.pegarUsuariosNoFirebase();
        ConfiguracaoFirebase.pegarEventosNoFirebase();
        ConfiguracaoFirebase.pegarInscricoesNoFirebase();

    }


    public void entrar(View view){

        boolean existe = false;

        for (int i = 0; i < ConfiguracaoFirebase.usuarios.size(); i++) {
            existe = isExiste(existe, i);

            if (existe){
                realizarLogin();
            }
        }

        if (!existe){
            Toast.makeText(this, "Usuario não encontrado!", Toast.LENGTH_SHORT).show();
        }


    }

    private void realizarLogin() {
        String emailUsuario = editTextEmail.getText().toString();
        MainActivity.usuarioLogado = UsuarioCases.logarUsuario(emailUsuario);
        Toast.makeText(this, "Usuario logado com sucesso!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        first();
        finish();
    }

    private boolean isExiste(boolean existe, int i) {
        if(ConfiguracaoFirebase.usuarios.get(i).getEmail().equals(editTextEmail.getText().toString())){
            if(ConfiguracaoFirebase.usuarios.get(i).getSenha().equals(editTextSenha.getText().toString())){
                existe = true;
            }
        }
        return existe;
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroUsuarioActivity.class));
    }


    private void setupViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextPassword);

    }

    private void first(){
        SharedPreferences mPreferences = getSharedPreferences("event", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("first", true);
        editor.commit();
        editor.apply();

    }
}