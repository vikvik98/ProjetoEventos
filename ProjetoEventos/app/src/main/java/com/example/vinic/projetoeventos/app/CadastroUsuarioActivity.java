package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.cases.UsuarioCases;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextCoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        setupViews();
    }

    public void efetuarCadastro(View view) {

        boolean existe = false;

        if (verificarCamposVazios()){
            for (int i = 0; i < UsuarioCases.usuarios.size(); i++) {

                if(UsuarioCases.usuarios.get(i).getEmail().equals(editTextEmail.getText().toString())){
                    Toast.makeText(this, "Este email já está cadastrado", Toast.LENGTH_SHORT).show();
                    existe = true;
                }
            }

            if (!existe){
                if(editTextSenha.getText().toString().equals(editTextCoSenha.getText().toString())){
                    realizarLogin();
                }else {
                    Toast.makeText(this, "A senha não bate com a senha de confirmação", Toast.LENGTH_SHORT).show();
                }
            }

            else if (UsuarioCases.usuarios.size() == 0){
                realizarLogin();
            }
        }
    }

    private boolean verificarCamposVazios() {
        if (editTextEmail.getText().toString().trim().equals("") || editTextNome.getText().toString().trim().equals("") || editTextCoSenha.getText().toString().trim().equals("") || editTextSenha.getText().toString().trim().equals("")){
            Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void realizarLogin() {
        String nomeUsuario = editTextNome.getText().toString();
        String senhaUsuario = editTextSenha.getText().toString();
        String emailUsuario = editTextEmail.getText().toString();

        UsuarioCases.cadastrarUsuario(nomeUsuario,senhaUsuario,emailUsuario);

        Toast.makeText(this, "Usuario cadastrado!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void setupViews() {
        editTextNome = findViewById(R.id.nome_cadastro);
        editTextEmail = findViewById(R.id.email_cadastro);
        editTextSenha = findViewById(R.id.senha_cadastro);
        editTextCoSenha = findViewById(R.id.confirmar_senha_cadastro);
    }
}