package com.example.kjorge.projeto.MenuFragmento.EditarDataBase;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjorge.projeto.MenuFragmento.Adapter.Adapter;
import com.example.kjorge.projeto.MenuFragmento.CadastroDataBase.CadProduto;
import com.example.kjorge.projeto.MenuFragmento.CadastroDataBase.MetodosDataBaseDAO;
import com.example.kjorge.projeto.R;

public class ListarEditarFragment extends Fragment {
    private MetodosDataBaseDAO db;
    private Button btn_listar, btn_editar;
    private RecyclerView recyclerView;
    private EditText edtCadastroNomeProduto, edtCadastroPreco, edtCadastroMarca, edtCadastroQuantidade, edtId;
    private TextView ativarEdicao, txt0, txt1, txt2, txt3, txt4;


    public ListarEditarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_produtos_eddd, container, false);


        //FindView
        btn_listar = view.findViewById(R.id.btn_listar);
        btn_editar = view.findViewById(R.id.btn_editar);
        recyclerView = view.findViewById(R.id.recyclerView);
        edtCadastroNomeProduto = view.findViewById(R.id.edtCadastroNomeProduto);
        edtCadastroPreco = view.findViewById(R.id.edtCadastroPreco);
        edtCadastroMarca = view.findViewById(R.id.edtCadastroMarca);
        edtCadastroQuantidade = view.findViewById(R.id.edtCadastroQuantidade);
        ativarEdicao = view.findViewById(R.id.ativarEdicao);
        txt0 = view.findViewById(R.id.txt0);
        txt1 = view.findViewById(R.id.txt1);
        txt2 = view.findViewById(R.id.txt2);
        txt3 = view.findViewById(R.id.txt3);
        txt4 = view.findViewById(R.id.txt4);
        edtId = view.findViewById(R.id.edtId);


        db = new MetodosDataBaseDAO(getContext());


        //Onclick
        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (db.ListarBancoProduto().isEmpty()){
                    Toast.makeText(getContext(),"Lista vazia, nenhum produto cadastrado.",Toast.LENGTH_SHORT).show();
                }else {

                    db.ListarBancoProduto();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(new Adapter(getContext(), db.ListarBancoProduto(), clickListner()));
                }

            }
        });


        ativarEdicao.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                edtCadastroNomeProduto.setVisibility(View.GONE);
                edtCadastroPreco.setVisibility(View.GONE);
                edtCadastroMarca.setVisibility(View.GONE);
                edtCadastroQuantidade.setVisibility(View.GONE);
                txt0.setVisibility(View.GONE);
                txt1.setVisibility(View.GONE);
                txt2.setVisibility(View.GONE);
                txt3.setVisibility(View.GONE);
                txt4.setVisibility(View.GONE);
                btn_editar.setVisibility(View.GONE);
                edtId.setVisibility(View.GONE);
                return false;
            }
        });
        ativarEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCadastroNomeProduto.setVisibility(View.VISIBLE);
                edtCadastroPreco.setVisibility(View.VISIBLE);
                edtCadastroMarca.setVisibility(View.VISIBLE);
                edtCadastroQuantidade.setVisibility(View.VISIBLE);
                txt0.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
                txt3.setVisibility(View.VISIBLE);
                txt4.setVisibility(View.VISIBLE);
                btn_editar.setVisibility(View.VISIBLE);
                edtId.setVisibility(View.VISIBLE);

            }
        });

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtCadastroNomeProduto.getText().toString();
                String preco = edtCadastroPreco.getText().toString();
                String marca = edtCadastroMarca.getText().toString();
                String quantidade = edtCadastroQuantidade.getText().toString();



                if (edtId.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite um id Valido", Toast.LENGTH_SHORT).show();
                } else {
                    db.alterar(new CadProduto(Integer.parseInt(edtId.getText().toString()), nome, preco, marca, quantidade));
                    Toast.makeText(getContext(), "Produto Alterado", Toast.LENGTH_SHORT).show();
                }
                edtId.setText("");
                edtCadastroNomeProduto.setText("");
                edtCadastroPreco.setText("");
                edtCadastroMarca.setText("");
                edtCadastroQuantidade.setText("");


            }
        });

        return view;
    }

    private Adapter.cliqueCard clickListner() {
        return new Adapter.cliqueCard() {
            @Override
            public void clickView(View view, int index) {

            }
        };


    }


    public static ListarEditarFragment newInstance() {
        ListarEditarFragment fragment = new ListarEditarFragment();
        return fragment;
    }
}
