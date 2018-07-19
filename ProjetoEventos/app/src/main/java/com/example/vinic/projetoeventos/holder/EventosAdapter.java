package com.example.vinic.projetoeventos.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.model.Evento;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {

    private Context context;
    private List<Evento> eventos;

    public EventosAdapter(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;

    }


    @Override
    public EventosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.card_evento, parent, false);

        return new EventosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Evento evento = this.eventos.get(position);

        holder.textViewNomeEvento.setText(evento.getNome());
        holder.textViewStatusEvento.setText(evento.getStatusDoEvento());
        holder.textViewLocalEvento.setText(evento.getLocal());
        holder.textViewDataEvento.setText(new SimpleDateFormat("dd/MM/yyyy").format(evento.getDataInicial()));
    }


    @Override
    public int getItemCount() {
        return eventos.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView textViewNomeEvento;
        protected TextView textViewLocalEvento;
        protected TextView textViewDataEvento;
        protected TextView textViewStatusEvento;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDataEvento = itemView.findViewById(R.id.data_evento);
            textViewNomeEvento = itemView.findViewById(R.id.nome_evento);
            textViewLocalEvento = itemView.findViewById(R.id.local_evento);
            textViewStatusEvento = itemView.findViewById(R.id.status_evento);

        }
    }

}
