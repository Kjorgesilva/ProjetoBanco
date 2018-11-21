package com.example.kjorge.projeto.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjorge.projeto.DataBase.DataBaseProduto.CadProduto;
import com.example.kjorge.projeto.R;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterOsHolder> {

    private Context contexto;
    private List<CadProduto> lista;
    private cliqueCard click;

    //construtor
    public Adapter(Context contexto, List<CadProduto> lista, cliqueCard click) {
        this.contexto = contexto;
        this.lista = lista;
        this.click = click;
    }


    @NonNull
    @Override
    public AdapterOsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //layout que vai exibir as informações
        View view = LayoutInflater.from(contexto).inflate(R.layout.adapter, parent, false);
        AdapterOsHolder holder = new AdapterOsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterOsHolder holder, final int position) {
        //pega as informaçoes que foram mapeadas
        holder.txItem1.setText(String.valueOf(lista.get(position).getId()));
        holder.txItem2.setText(lista.get(position).getNome());
        holder.txItem3.setText(String.valueOf(lista.get(position).getPreco()));
        holder.txItem4.setText(lista.get(position).getMarca());
        holder.txItem5.setText(String.valueOf(lista.get(position).getQuantidade()));

        if (click != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    //possição do card
                    click.clickView(holder.itemView, position);


                }
            });
        }
    }
    //exibi a lista toda
    @Override
    public int getItemCount() {
        if (lista.size()>0) {
            return lista.size();
        } else {
            return 0;
        }

    }

    public interface cliqueCard {
        void clickView(View view, int index);
    }

    public static class AdapterOsHolder extends RecyclerView.ViewHolder {
        View view;
        TextView txItem1, txItem2, txItem3, txItem4, txItem5;


        public AdapterOsHolder(View itemView) {
            super(itemView);

            this.view = itemView;

            txItem1 = view.findViewById(R.id.txtid);
            txItem2 = view.findViewById(R.id.txtNome);
            txItem3 = view.findViewById(R.id.txtPreco);
            txItem4 = view.findViewById(R.id.txtMarca);
            txItem5 = view.findViewById(R.id.txtQuantidade);


        }
    }

}
