package com.example.kjorge.projeto.DataBase.DataBaseUsuario;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjorge.projeto.Adapter.Adapter;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.Cadastro;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.CadastroDao;
import com.example.kjorge.projeto.LoginActivity;
import com.example.kjorge.projeto.helpInterface.InterfaceHelp;
import com.example.kjorge.projeto.R;

import java.util.ArrayList;

public class CadastrarNovoClienteActivity extends AppCompatActivity implements InterfaceHelp {

    private EditText edtLoginCadastor, edtSenhaCadastro;
    private Button btnCadastrar;
    private Context contexto = this;
    private Handler handler;
    private TextView txtSenhaGrande, txt_excluir;


    //serve para fazer a ligação com a classe CadastroDAO e chama os metodos do Banco
    CadastroDao db = new CadastroDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_novo_cliente);

        FindView();
        OnClick();
    }

    @Override
    public void FindView() {
        edtLoginCadastor = findViewById(R.id.edtLoginCadastor);
        edtSenhaCadastro = findViewById(R.id.edtSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtSenhaGrande = findViewById(R.id.txtSenhaGrande);
        handler = new Handler();
        txt_excluir = findViewById(R.id.txt_excluirUsuario);

        edtSenhaCadastro.addTextChangedListener(texto);
    }

    //para usar o limite maximo de caracter...
    final private TextWatcher texto = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            txtSenhaGrande.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() <= 7) {
                txtSenhaGrande.setVisibility(View.GONE);
            } else {
                txtSenhaGrande.setVisibility(View.VISIBLE);
            }

        }
    };

    @Override
    public void OnClick() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = edtLoginCadastor.getText().toString();
                String senha = edtSenhaCadastro.getText().toString();
                Boolean achou = false;


                if (usuario.equals("") || senha.equals("")) {
                    edtLoginCadastor.setError("Texto Obrigatorio!!");
                    edtSenhaCadastro.setError("Texto Obrigatorio!!");

                } else {
                    for (int i = 0; i < db.ListarBanco().size(); i++) {
                        if (usuario.equals(db.ListarBanco().get(i).getUsuario())  && senha.equals(db.ListarBanco().get(i).getSenha())) {
                            achou = true;
                        }
                    }
                    if (achou) {
                        alerta();
                        edtLoginCadastor.setText("");
                        edtSenhaCadastro.setText("");

                    } else {
                        db.inserir(new Cadastro(usuario, senha));
                        Toast.makeText(contexto, "Usuario Adicionado", Toast.LENGTH_LONG).show();

                        handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    finish();
                                                    startActivity(new Intent(contexto, LoginActivity.class));
                                                }
                                            }, 1000
                        );
                    }
                }

            }
        });

        txt_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuario = edtLoginCadastor.getText().toString();
                String senha = edtSenhaCadastro.getText().toString();
                Boolean achou = false;


                if (!db.ListarBanco().isEmpty()) {
                    for (int i = 0; i < db.ListarBanco().size(); i++) {
                        if (usuario.equals(db.ListarBanco().get(i).getUsuario()) && senha.equals(db.ListarBanco().get(i).getSenha())) {
                            achou = true;
                        }
                    }if (achou){
                        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                        builder.setIcon(R.drawable.delete);
                        builder.setTitle("Excluir usuario");
                        builder.setMessage("Tem certeza que deseja excluir o usuario " + usuario);

                        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.excluir(usuario);
                                Toast.makeText(contexto, "Usuario Excluido", Toast.LENGTH_SHORT).show();
                                edtSenhaCadastro.setText("");
                                edtLoginCadastor.setText("");
                            }
                        });
                        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();
                    }else{
                        AlertDialog.Builder alertaExcliur = new AlertDialog.Builder(contexto);

                        alertaExcliur.setTitle("Informação");
                        alertaExcliur.setIcon(R.drawable.delete);
                        alertaExcliur.setMessage("Para excluir digite o login e a senha do usuario que quer excluir");
                        alertaExcliur.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertaExcliur.show();
                        edtSenhaCadastro.setText("");
                        edtLoginCadastor.setText("");
                    }
                }
            }
        });


    }

    public void alerta() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Informação");
        alerta.setIcon(R.drawable.ic_addcliente24dp);
        alerta.setMessage("Esse usuario ja se encontra cadastrado no sistema, volte e tente fazer Login Novamente ou cadastre um novo usuario");
        alerta.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alerta.setNegativeButton("Novo Cadastro", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        alerta.show();

    }


}