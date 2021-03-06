package com.example.kjorge.projeto.MenuFragmento;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjorge.projeto.Adapter.Adapter;
import com.example.kjorge.projeto.DataBase.DataBaseProduto.CadProduto;
import com.example.kjorge.projeto.DataBase.DataBaseProduto.MetodosDataBaseDAO;
import com.example.kjorge.projeto.R;

public class ListarEditarFragment extends Fragment {
    private MetodosDataBaseDAO db;
    private Button btn_editar;
    private RecyclerView recyclerView;
    private EditText edtCadastroNomeProduto, edtCadastroPreco, edtCadastroMarca, edtCadastroQuantidade, edtId;
    private TextView txt0, txt1, txt2, txt3, txt4;
    private TextView textoMaximo, textoMaximoQuantidade;
    private CheckBox ativarEdicao;

    public ListarEditarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_listar_produtos_eddd, container, false);

        //FindView
        btn_editar = view.findViewById(R.id.btn_editar);
        recyclerView = view.findViewById(R.id.recyclerView);
        edtCadastroNomeProduto = view.findViewById(R.id.edtCadastroNomeProduto);
        edtCadastroPreco = view.findViewById(R.id.edtCadastroPreco);
        edtCadastroMarca = view.findViewById(R.id.edtCadastroMarca);
        edtCadastroQuantidade = view.findViewById(R.id.edtCadastroQuantidade);
        ativarEdicao = view.findViewById(R.id.ativarEdicao);
        textoMaximo = view.findViewById(R.id.textMaximo);
        textoMaximoQuantidade = view.findViewById(R.id.textMaximoQuantidade);
        txt0 = view.findViewById(R.id.txt0);
        txt1 = view.findViewById(R.id.txt1);
        txt2 = view.findViewById(R.id.txt2);
        txt3 = view.findViewById(R.id.txt3);
        txt4 = view.findViewById(R.id.txt4);
        edtId = view.findViewById(R.id.edtId);

        //limite maximo de caracter que pode digita
        final TextWatcher texto = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textoMaximo.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() <= 9) {
                    textoMaximo.setVisibility(View.GONE);
                } else {
                    textoMaximo.setVisibility(View.VISIBLE);
                }

            }
        };
        final TextWatcher textMaximoQuantidade2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textoMaximoQuantidade.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() <= 9) {
                    textoMaximoQuantidade.setVisibility(View.GONE);
                } else {
                    textoMaximoQuantidade.setVisibility(View.VISIBLE);
                }

            }
        };

        edtCadastroPreco.addTextChangedListener(texto);
        edtCadastroQuantidade.addTextChangedListener(textMaximoQuantidade2);
        db = new MetodosDataBaseDAO(getContext());

        //Onclick
        if (db.ListarBancoProduto().isEmpty()) {
            Toast.makeText(getContext(), "Lista vazia, nenhum produto cadastrado.", Toast.LENGTH_SHORT).show();
        } else {
            db.ListarBancoProduto();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new Adapter(getContext(), db.ListarBancoProduto(), clickListner()));
        }
        ativarEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ativarEdicao.isChecked()) {
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
                    edtCadastroPreco.setText("0");
                    edtCadastroQuantidade.setText("0");

                } else {
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
                    textoMaximo.setVisibility(View.GONE);
                    edtId.setVisibility(View.GONE);

                    edtId.setText("");
                    edtCadastroNomeProduto.setText("");
                    edtCadastroMarca.setText("");
                    edtCadastroPreco.setText("0");
                    edtCadastroQuantidade.setText("0");

                }
            }
        });
        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edtCadastroNomeProduto.getText().toString();
                int preco = Integer.parseInt(edtCadastroPreco.getText().toString());
                String marca = edtCadastroMarca.getText().toString();
                int quantidade = Integer.parseInt(edtCadastroQuantidade.getText().toString());

                if (edtId.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Digite um id Valido", Toast.LENGTH_SHORT).show();
                } else {
                    db.alterar(new CadProduto(Integer.parseInt(edtId.getText().toString()), nome, preco, marca, quantidade));
                    Toast.makeText(getContext(), "Produto Alterado", Toast.LENGTH_SHORT).show();

                }

                edtId.setText("");
                edtCadastroNomeProduto.setText("");
                edtCadastroMarca.setText("");
                edtCadastroPreco.setText(String.valueOf(""));
                edtCadastroQuantidade.setText(String.valueOf(""));
                ativarEdicao.setChecked(false);

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

                db.ListarBancoProduto();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new Adapter(getContext(), db.ListarBancoProduto(), clickListner()));
            }
        });
        return view;
    }

    private Adapter.cliqueCard clickListner() {
        return new Adapter.cliqueCard() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void clickView(View view, int index) {

                if (ativarEdicao.isChecked()) {
                    edtId.setText(String.valueOf(db.ListarBancoProduto().get(index).getId()));
                    edtCadastroNomeProduto.setText(db.ListarBancoProduto().get(index).getNome());
                    edtCadastroPreco.setText(String.valueOf(db.ListarBancoProduto().get(index).getPreco()));
                    edtCadastroMarca.setText(db.ListarBancoProduto().get(index).getMarca());
                    edtCadastroQuantidade.setText(String.valueOf(db.ListarBancoProduto().get(index).getQuantidade()));
                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                    alerta.setTitle("ALERTA!");
                    alerta.setMessage("Para editar o produto é necessario marcar o CheckBox de edição");
                    alerta.setIcon(R.drawable.check_24dp);
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alerta.show();
                }
            }
        };
    }

    public static ListarEditarFragment newInstance() {
        ListarEditarFragment fragment = new ListarEditarFragment();
        return fragment;
    }
}
