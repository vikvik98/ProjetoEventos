package com.example.vinic.projetoeventos.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.vinic.projetoeventos.R;
import com.example.vinic.projetoeventos.app.MainActivity;
import com.example.vinic.projetoeventos.cases.InscricaoCases;
import com.example.vinic.projetoeventos.cases.ItemClickListener;
import com.example.vinic.projetoeventos.model.Atividade;
import com.example.vinic.projetoeventos.model.Inscricao;

import java.util.ArrayList;
import java.util.List;

public class ListaAtividadesRVAdapter extends RecyclerView.Adapter<ListaAtividadesRVAdapter.ViewHolder> {

    private final Context context;
    private final List<Atividade> atividades;
    public List<Atividade> atividadesChecadas = new ArrayList<>();

    //public Atividade[] atividadesChecadas = new ArrayList<>();

    public ListaAtividadesRVAdapter(Context context, List<Atividade> atividades){

        this.context = context;
        this.atividades = atividades;
    }

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
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox cbInscricao = (CheckBox) v;

                //CHECAR SE ESTAR CHECADO OU NÃO
                if(cbInscricao.isChecked()){

                    atividadesChecadas.add(atividades.get(pos));
                }else if(!cbInscricao.isChecked()){

                    atividadesChecadas.remove(atividades.get(pos));
                }
            }
        });

        if(atividade.getKeyCriador().equals(MainActivity.usuarioLogado.getId())){
            holder.cbInscricao.setVisibility(View.GONE);
        }else{
            holder.cbInscricao.setVisibility(View.VISIBLE);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InscricaoCases.inserirAtividadeNaInscricao(MainActivity.usuarioLogado.getId(),atividade.getKeyEvento(),atividade);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return atividades.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView tvAtividadesNome;
        protected  TextView tvAtividadesdata;
        protected CheckBox cbInscricao;
        protected ItemClickListener itemClickListener;

        public ViewHolder(View itemView){
            super(itemView);

            tvAtividadesNome = (TextView) itemView.findViewById(R.id.nome_atividade);
            tvAtividadesdata = (TextView) itemView.findViewById(R.id.data_atividade);
            cbInscricao = itemView.findViewById(R.id.checkbox_inscricao);

            cbInscricao.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;

    }


        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }
    }



}
