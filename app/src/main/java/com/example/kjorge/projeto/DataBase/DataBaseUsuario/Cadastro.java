package com.example.kjorge.projeto.DataBase.DataBaseUsuario;

import java.io.Serializable;

public class Cadastro implements Serializable {

    private int id;
    private String usuario;
    private String senha;

    public Cadastro(String usuario, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Cadastro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
