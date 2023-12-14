package com.example.casapopular.dominio.builder;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamiliaBuilder {
    private List<Pessoa> pessoas = new ArrayList<>();

    public Familia criar(){
        return new Familia(pessoas);
    }

    public FamiliaBuilder comPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }

    public FamiliaBuilder comPessoas(Pessoa... pessoas) {
        this.pessoas = Arrays.asList(pessoas);
        return this;
    }
}
