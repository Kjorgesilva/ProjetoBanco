package com.example.kjorge.projeto.MenuFragmento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjorge.projeto.Adapter.Adapter;
import com.example.kjorge.projeto.DataBase.DataBaseProduto.MetodosDataBaseDAO;
import com.example.kjorge.projeto.R;

public class ListarCompraFragment extends Fragment {
    private MetodosDataBaseDAO db;
    private RecyclerView recyclerView;
    private TextView txt_valorTotal, txt_totalItem;
    private int valorfinal = 0, qtdItem = 0;




    public ListarCompraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_compra, container, false);
        db = new MetodosDataBaseDAO(getContext());

        //FindView mapea as variaveis
        recyclerView = view.findViewById(R.id.recyclerView);
        txt_valorTotal = view.findViewById(R.id.txt_valorTotal);
        txt_totalItem = view.findViewById(R.id.txt_totalItem);


        if (db.ListarBancoProduto().isEmpty()) {
            Toast.makeText(getContext(), "vazio", Toast.LENGTH_SHORT).show();

        } else {
            //listar todos os itens cadastrados...
            db.ListarBancoProduto();
            //setLayoutManager para exibir o recyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new Adapter(getContext(), db.ListarBancoProduto(), clickListner()));


            for (int i = 0; i < db.ListarBancoProduto().size(); i++) {
                valorfinal += db.ListarBancoProduto().get(i).getPreco();
                qtdItem += db.ListarBancoProduto().get(i).getQuantidade();


            }
            txt_valorTotal.setText(String.valueOf(valorfinal));
            txt_totalItem.setText(String.valueOf(qtdItem));

        }
        return view;
    }


    public static ListarCompraFragment newInstance() {
        ListarCompraFragment fragment = new ListarCompraFragment();
        return fragment;
    }

    private Adapter.cliqueCard clickListner() {
        return new Adapter.cliqueCard() {
            @Override
            public void clickView(View view, int index) {
            }
        };


    }

}
