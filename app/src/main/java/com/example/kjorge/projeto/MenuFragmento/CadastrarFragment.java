package com.example.kjorge.projeto.MenuFragmento;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.kjorge.projeto.DataBase.DataBaseProduto.CadProduto;
import com.example.kjorge.projeto.DataBase.DataBaseProduto.MetodosDataBaseDAO;
import com.example.kjorge.projeto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarFragment extends Fragment {

    private Button btn_cadastrar;
    private EditText edtCadastroNomeProduto, edtCadastroPreco, edtCadastroMarca, edtCadastroQuantidade;
    private MetodosDataBaseDAO db;
    private boolean achou = false;


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
        edtCadastroPreco.setText("0");
        edtCadastroQuantidade.setText("0");



        //OnClick
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtCadastroNomeProduto.getText().toString();
                String marca = edtCadastroMarca.getText().toString();
                int preco = Integer.parseInt(edtCadastroPreco.getText().toString());
                int quantidade = Integer.parseInt(edtCadastroQuantidade.getText().toString());



                if (nome.equals("") || marca.equals("") || edtCadastroPreco.getText().toString().equals("0")|| edtCadastroQuantidade.getText().toString().equals("0")) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                    alerta.setTitle("Informação");
                    alerta.setIcon(R.drawable.erro_24dp);
                    alerta.setMessage("Preencha todos os itens para cadastrar o produto");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edtCadastroPreco.setText("0");
                            edtCadastroQuantidade.setText("0");
                        }
                    });
                    alerta.show();

                } else {
                    for (int i = 0; i < db.ListarBancoProduto().size(); i++) {
                        if (nome.equals(db.ListarBancoProduto().get(i).getNome())) {
                            achou = true;
                        }

                    }
                    if (achou) {
                        alerta();
                    } else {
                        db.inserir(new CadProduto(nome, preco, marca, quantidade));
                        Toast.makeText(getContext(), "Produto Adicionado", Toast.LENGTH_LONG).show();

                    }
                }
                edtCadastroNomeProduto.setText("");
                edtCadastroMarca.setText("");
                edtCadastroPreco.setText("0");
                edtCadastroQuantidade.setText("0");
            }
        });
        return view;
    }

    public static CadastrarFragment newInstance() {
        CadastrarFragment fragment = new CadastrarFragment();
        return fragment;
    }

    public void alerta() {

        AlertDialog.Builder alerta_ja_tem = new AlertDialog.Builder(getContext());
        alerta_ja_tem.setTitle("Informação");
        alerta_ja_tem.setMessage("O produto já esta cadastrado no sistema");
        alerta_ja_tem.setIcon(R.drawable.carrinho);
        alerta_ja_tem.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerta_ja_tem.show();


    }


}
