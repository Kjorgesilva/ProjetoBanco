package com.example.kjorge.projeto.DataBaseUsuario;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjorge.projeto.LoginActivity;
import com.example.kjorge.projeto.helpInterface.InterfaceHelp;
import com.example.kjorge.projeto.R;

public class CadastrarNovoClienteActivity extends AppCompatActivity implements InterfaceHelp {

    private EditText edtLoginCadastor, edtSenhaCadastro;
    private Button btnCadastrar;
    private Context contexto = this;
    private Handler handler;
    private TextView txtSenhaGrande;
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

        edtSenhaCadastro.addTextChangedListener(texto);
    }

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

                if (usuario.equals("")) {
                    edtLoginCadastor.setError("Texto Obrigatorio!!");

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
        });


    }
}