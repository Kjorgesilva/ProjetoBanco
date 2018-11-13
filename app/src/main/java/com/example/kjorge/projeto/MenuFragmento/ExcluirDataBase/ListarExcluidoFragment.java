package com.example.kjorge.projeto.MenuFragmento.ExcluirDataBase;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kjorge.projeto.MenuFragmento.Adapter.Adapter;
import com.example.kjorge.projeto.MenuFragmento.CadastroDataBase.MetodosDataBaseDAO;
import com.example.kjorge.projeto.R;

public class ListarExcluidoFragment extends Fragment {
    private Button btn_listar;
    private Button btn_excluir;
    private MetodosDataBaseDAO db;
    private RecyclerView recyclerView;
    private EditText edt_excluir;


    public ListarExcluidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_excluir, container, false);
//contexto
        db = new MetodosDataBaseDAO(getContext());


        //FindView
        btn_listar = view.findViewById(R.id.btn_listar);
        btn_excluir = view.findViewById(R.id.btn_excluir);
        recyclerView = view.findViewById(R.id.recyclerView);
        edt_excluir = view.findViewById(R.id.edt_excluir);


        //OnClick
        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.ListarBancoProduto().isEmpty()){
                    Toast.makeText(getContext(),"Lista vazia, nenhum produto cadastrado.",Toast.LENGTH_SHORT).show();
                }else{

                db.ListarBancoProduto();
                //setLayoutManager para exibir o recyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new Adapter(getContext(), db.ListarBancoProduto(), clickListner()));
            }}
        });

        btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edt_excluir.getText().toString();

                if (db.ListarBancoProduto().isEmpty()) {
                    Toast.makeText(getContext(), "Digite um nome valido para excluir da lista...", Toast.LENGTH_SHORT).show();
                }else {

                    for (int i = 0; i  < db.ListarBancoProduto().size();i++){
                        if (nome.equals(db.ListarBancoProduto().get(i).getNome())){

                            db.excluir(nome);
                            Toast.makeText(getContext(), "Produto Excluido", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Produto não encontrado...", Toast.LENGTH_SHORT).show();
                        }

                    }


                }

            }
        });

        return view;
    }

    public static ListarExcluidoFragment newInstance() {
        ListarExcluidoFragment fragment = new ListarExcluidoFragment();
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
