package com.example.kjorge.projeto.MenuFragmento;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.kjorge.projeto.DataBase.DataBaseUsuario.Cadastro;
import com.example.kjorge.projeto.DataBase.DataBaseUsuario.CadastroDao;
import com.example.kjorge.projeto.DataBase.DatabaseHelper;
import com.example.kjorge.projeto.LoginActivity;
import com.example.kjorge.projeto.helpInterface.InterfaceHelp;
import com.example.kjorge.projeto.R;

public class MainActivity extends AppCompatActivity implements InterfaceHelp {

    private BottomNavigationView bottom_nav_view;
    private Toolbar toolbar;
    private int contador = 0;
    private ListarExcluidoFragment fragment_excluir;
    private ListarEditarFragment fragment_edita;
    private ListarCompraFragment fragment_compra;
    private CadastrarFragment fragment_home;
    private CadastroDao db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindView();
        OnClick();



    }
    @Override
    public void FindView() {

        bottom_nav_view = findViewById(R.id.bottom_nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Estoque");


    }
    @Override
    public void OnClick() {

        bottom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_cadastro:
                        selectedFragment = CadastrarFragment.newInstance();
                        break;
                    case R.id.navigation_editar:
                        selectedFragment = ListarEditarFragment.newInstance();
                        break;
                    case R.id.navigation_deletar:
                        selectedFragment = ListarExcluidoFragment.newInstance();
                        break;

                    case R.id.navigation_carrinho:
                        selectedFragment = ListarCompraFragment.newInstance();
                        break;



                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.layout_frame, selectedFragment);
                transaction.commit();
                return true;

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigation_cadastro:
                if (contador == 1) {
                    setFragment(fragment_excluir);
                    bottom_nav_view.setSelectedItemId(R.id.navigation_cadastro);
                    contador = 0;

                } else {

                    finish();
                }

        }

        return true;
    }
    private void setFragment(Fragment valor_frag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navigation_cadastro, valor_frag);
        fragmentTransaction.commit();
    }


}
