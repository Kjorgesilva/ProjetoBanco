package com.example.kjorge.projeto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kjorge.projeto.DataBase.DataBaseUsuario.CadastrarNovoClienteActivity;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.CadastroDao;
import com.example.kjorge.projeto.MenuFragmento.MainActivity;
import com.example.kjorge.projeto.helpInterface.InterfaceHelp;

public class LoginActivity extends AppCompatActivity implements InterfaceHelp {

    private ImageView imgAPP;
    private EditText edtLogin, edtSenha;
    private Button btnEnter;
    private ProgressBar progressBar;
    private TextView txCadastrarNovo, txtSenhaGrande;
    private Context contexto = this;
    private Handler handler;


    //serve para fazer a ligação com a classe MetodosDataBaseDAO e chama os metodos do Banco
    CadastroDao db = new CadastroDao(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FindView();
        OnClick();

    }

    @Override
    public void FindView() {
        imgAPP = findViewById(R.id.imgAPP);
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        btnEnter = findViewById(R.id.btnEnter);
        progressBar = findViewById(R.id.progressBar);
        txCadastrarNovo = findViewById(R.id.txCadastrarNovo);
        txtSenhaGrande = findViewById(R.id.txtSenhaGrande);
        handler = new Handler();

        //metodo para implementar o texto quando passa o maximo de caracter ==8
//        edtSenha.addTextChangedListener(texto);
    }

    //metodo para implementar o texto quando passa o maximo de caracter ==8
//    private final TextWatcher texto = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            txtSenhaGrande.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            if (editable.length() <= 7) {
//                txtSenhaGrande.setVisibility(View.GONE);
//            } else {
//                txtSenhaGrande.setVisibility(View.VISIBLE);
//            }
//
//
//        }
//    };

    @Override
    public void OnClick() {

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int achou = 0;
                String usuario = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                if (db.ListarBanco().isEmpty()) {
                    alertaNaoTemUsuario();
                } else {

                    if (usuario.equals("")) {
                        edtLogin.setError("Texto Obrigatorio!!");

                    } else {
                        for (int i = 0; i < db.ListarBanco().size(); i++) {

                            if (usuario.equals(db.ListarBanco().get(i).getUsuario()) && senha.equals(db.ListarBanco().get(i).getSenha())) {
                                achou = 1;
                            }
                            if (achou == 1) {
                                progressBar.setVisibility(View.VISIBLE);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(contexto, MainActivity.class);
                                        startActivity(intent);
                                        edtLogin.setText("");
                                        edtSenha.setText("");
                                    }
                                }, 2000);
                            } else {
                                achou = 2;
                            }
                        }
                        if (achou >= 2) {
                            alerta();
                        }
                    }
                }
            }
        });

        txCadastrarNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, CadastrarNovoClienteActivity.class);
                startActivity(intent);
            }
        });


    }

    public void alerta() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
        alerta.setIcon(R.drawable.ic_addcliente24dp);
        alerta.setTitle("Informação");
        alerta.setMessage("Login ou Senha incorretas...");

        alerta.setPositiveButton("Cadastrar usario", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, CadastrarNovoClienteActivity.class);
                startActivity(intent);
            }
        });
        alerta.setNegativeButton("Tentar novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edtLogin.setText("");
                edtSenha.setText("");

            }
        });
        alerta.show();

    }

    public void alertaNaoTemUsuario() {

        AlertDialog.Builder alertaNaoTemUsuario = new AlertDialog.Builder(contexto);
        alertaNaoTemUsuario.setTitle("Alerta!");
        alertaNaoTemUsuario.setIcon(R.drawable.ic_addcliente24dp);
        alertaNaoTemUsuario.setMessage("Não possui nenhum usuario cadastrado...");
        alertaNaoTemUsuario.setPositiveButton("Cadastrar usuario", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, CadastrarNovoClienteActivity.class);
                startActivity(intent);
            }
        });
        alertaNaoTemUsuario.show();
    }


}
