package com.example.kjorge.projeto.DataBase.DataBaseProduto;

import java.io.Serializable;

public class CadProduto implements Serializable {


    private int id;
    private String nome;
    private int preco;
    private String marca;
    private int quantidade;

    public CadProduto() {
    }

    public CadProduto(String nome, int preco, String marca, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.quantidade = quantidade;
    }

    public CadProduto(int id, String nome, int preco, String marca, int quantidade) {
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

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
