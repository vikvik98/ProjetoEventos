package com.example.vinic.projetoeventos.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.model.Atividade;

import java.util.List;

public class ListaAtividadesRVAdapter extends RecyclerView.Adapter<ListaAtividadesRVAdapter.ViewHolder> {

    private final Context context;
    private final List<Atividade> atividades;
    @NonNull
    @Override
    public ListaAtividadesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_atividades,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAtividadesRVAdapter.ViewHolder holder, int position) {

        final Atividade atividade = this.atividades.get(position);
        holder.tvAtividadesNome.setText(atividade.getNome());
        holder.tvAtividadesdata.setText(atividade.getHoraInicio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //O Que fazer quando clickar no card...
            }
        });
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView tvAtividadesNome;
        protected  TextView tvAtividadesdata;

        public ViewHolder(View itemView){
            super(itemView);

            tvAtividadesNome = (TextView) itemView.findViewById(R.id.nome_atividade);
            tvAtividadesdata = (TextView) itemView.findViewById(R.id.data_atividade);
        }

    }

    public ListaAtividadesRVAdapter(Context context, List<Atividade> atividades){

        this.context = context;
        this.atividades = atividades;
    }

}
