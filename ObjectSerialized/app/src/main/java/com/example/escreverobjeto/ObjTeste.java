package com.example.escreverobjeto;

import java.io.Serializable;

public class ObjTeste implements Serializable {
    public int codigo;
    public String descricao;

    public ObjTeste(int cod, String desc){
        this.codigo = cod;
        this.descricao = desc;
    }
}
