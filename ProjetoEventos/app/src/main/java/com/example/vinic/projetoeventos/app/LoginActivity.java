package com.example.vinic.projetoeventos.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.controller.UsuarioController;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static List<Usuario> usuarios;
    public static Usuario usuario;
    DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference().child("usuarios");
    private EditText editTextEmail;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();
        usuarios = new ArrayList<>();
        pegarUsuariosNoFirebase();
    }


    public void pegarUsuariosNoFirebase(){
        usuarios.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapsshot : dataSnapshot.getChildren()){
                    Usuario user = postSnapsshot.getValue(Usuario.class);
                    usuarios.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void entrar(View view){
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getEmail().equals(editTextEmail.getText().toString())){
                if(usuarios.get(i).getSenha().equals(editTextSenha.getText().toString())){
                    usuario = UsuarioController.logarUsuario(editTextEmail);
                    Toast.makeText(this, "Usuario Logado com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Senha do usuario errada!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Email não encontrado!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }


    private void setupViews() {
        editTextEmail = findViewById(R.id.id_email);
        editTextSenha = findViewById(R.id.id_senha);

    }

}