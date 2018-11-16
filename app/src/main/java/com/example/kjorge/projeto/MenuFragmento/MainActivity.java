package com.example.kjorge.projeto.MenuFragmento;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.kjorge.projeto.LoginActivity;
import com.example.kjorge.projeto.helpInterface.InterfaceHelp;
import com.example.kjorge.projeto.R;

public class MainActivity extends AppCompatActivity implements InterfaceHelp {

    private BottomNavigationView bottom_nav_view;


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

                    case R.id.Sair:
                        System.exit(0);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                        startActivity(intent);

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

    public void alerta() {


    }
}
