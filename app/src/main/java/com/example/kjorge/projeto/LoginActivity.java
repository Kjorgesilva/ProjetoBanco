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
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.CadastrarNovoClienteActivity;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.CadastroDao;
import com.example.kjorge.projeto.MenuFragmento.MainActivity;
import com.example.kjorge.projeto.Web.UsuarioServices;
import com.example.kjorge.projeto.helpInterface.InterfaceHelp;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements InterfaceHelp {

//    private ImageView imgAPP;
    private EditText edtLogin, edtSenha;
    private Button btnEnter;
    private ProgressBar progressBar;
    private TextView txCadastrarNovo;
    private Context contexto = this;
    private Handler handler;
    private Button btn_quantidade,btn_logg;
    private UsuarioServices usuarioServices;



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
//        imgAPP = findViewById(R.id.imgAPP);
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        btnEnter = findViewById(R.id.btnEnter);
        progressBar = findViewById(R.id.progressBar);
        txCadastrarNovo = findViewById(R.id.txCadastrarNovo);
        handler = new Handler();
        btn_quantidade = findViewById(R.id.btn_qauntidade);
        btn_logg = findViewById(R.id.btn_logg);
        usuarioServices = new UsuarioServices(contexto);
    }

    @Override
    public void OnClick() {

        btn_logg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> map = new HashMap<>();

                String nome = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();
                map.put("nome",nome);
                map.put("senha",senha);
                usuarioServices.doPost("users/login",map);

                if (nome.isEmpty() && senha.isEmpty()){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
                    alerta.setView(R.layout.alerta_login_webservice);
                    alerta.show();
                }
                //usa o metodo GET
                //usuarioServices.getUsuario("users/users");
            }
        });
        btn_quantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantidade = 0;
                for (int o = 0; o < db.ListarBanco().size(); o++) {
                    quantidade = db.ListarBanco().size();
                }

                AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
                alerta.setTitle("Informações");
                alerta.setMessage("Quantidade de Usuarios:  " + quantidade + " Cadastrado");
                alerta.setIcon(R.drawable.check_24dp);
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alerta.show();


            }
        });


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
                            alertaSenhaIncorreta();
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

    public void alertaSenhaIncorreta() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
        alerta.setView(R.layout.alertasenhaincorreta);

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
        alertaNaoTemUsuario.setView(R.layout.alertanaotemusuario);
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
