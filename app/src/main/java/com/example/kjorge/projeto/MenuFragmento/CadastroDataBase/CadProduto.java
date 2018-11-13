package com.example.kjorge.projeto.MenuFragmento.CadastroDataBase;

import java.io.Serializable;

public class CadProduto implements Serializable {


    private int id;
    private String nome;
    private String preco;
    private String marca;
    private String quantidade;

    public CadProduto() {
    }

    public CadProduto(String nome, String preco, String marca, String quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.quantidade = quantidade;
    }

    public CadProduto(int id, String nome, String preco, String marca, String quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.quantidade = quantidade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
