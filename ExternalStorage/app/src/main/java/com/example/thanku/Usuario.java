package com.example.thanku;

import java.io.Serializable;

class Usuario implements Serializable {
        private String Nome;
        private Integer Idade;
        private String Telefone;

    public Usuario(String nome, int idade, String telefone) {
        Nome = nome;
        Idade = idade;
        Telefone = telefone;
    }

    public String getNome(){
        return Nome;
    }

    public String getTelefone(){
        return Telefone;
    }

    public Integer getIdade(){
        return Idade;
    }
}
