package com.example.vinic.projetoeventos.holder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.app.EventoActivity;
import com.example.vinic.projetoeventos.app.InscricaoActivity;
import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.dao.ConfiguracaoFirebase;
import com.example.vinic.projetoeventos.model.Evento;
import com.example.vinic.projetoeventos.model.Inscricao;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class InscricoesAdapter extends RecyclerView.Adapter<InscricoesAdapter.ViewHolder> {

    private Context context;
    private List<Inscricao> inscricaos;

    public InscricoesAdapter(Context context, List<Inscricao> inscricaos) {
        this.context = context;
        this.inscricaos = inscricaos;
    }


    @Override
    public InscricoesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.card_evento, parent, false);

        return new InscricoesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Inscricao inscricao = this.inscricaos.get(position);

        holder.textViewNomeUsuario.setText(ConfiguracaoFirebase.nomeUsuario(inscricao.getKeyUsuario()));

    }

    @Override
    public int getItemCount() {
        return inscricaos.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {


        protected TextView textViewNomeUsuario;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewNomeUsuario = itemView.findViewById(R.id.status_evento);

        }
    }

}
