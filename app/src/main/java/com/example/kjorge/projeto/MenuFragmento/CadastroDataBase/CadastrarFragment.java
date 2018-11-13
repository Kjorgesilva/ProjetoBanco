package com.example.kjorge.projeto.MenuFragmento.CadastroDataBase;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kjorge.projeto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarFragment extends Fragment {

    private Button btn_cadastrar;
    private EditText edtCadastroNomeProduto, edtCadastroPreco, edtCadastroMarca, edtCadastroQuantidade;
    private MetodosDataBaseDAO db;


    public CadastrarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MetodosDataBaseDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastrar, container, false);


        //FindView
        btn_cadastrar = view.findViewById(R.id.btn_cadastro);
        edtCadastroNomeProduto = view.findViewById(R.id.edtCadastroNomeProduto);
        edtCadastroPreco = view.findViewById(R.id.edtCadastroPreco);
        edtCadastroMarca = view.findViewById(R.id.edtCadastroMarca);
        edtCadastroQuantidade = view.findViewById(R.id.edtCadastroQuantidade);


        //OnClick
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtCadastroNomeProduto.getText().toString();
                String preco = edtCadastroPreco.getText().toString();
                String marca = edtCadastroMarca.getText().toString();
                String quantidade = edtCadastroQuantidade.getText().toString();

                if (edtCadastroNomeProduto.getText().toString().equals("")) {
                    edtCadastroNomeProduto.setError("Texto Obrigatorio!!");

                } else {
                    db.inserir(new CadProduto(nome, preco, marca, quantidade));
                    Toast.makeText(getContext(), "Produto Adicionado", Toast.LENGTH_LONG).show();

                }
                edtCadastroNomeProduto.setText("");
                edtCadastroPreco.setText("");
                edtCadastroMarca.setText("");
                edtCadastroQuantidade.setText("");
            }
        });
        return view;
    }

    public static CadastrarFragment newInstance() {
        CadastrarFragment fragment = new CadastrarFragment();
        return fragment;
    }


}
