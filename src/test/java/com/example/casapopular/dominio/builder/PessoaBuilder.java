package com.example.casapopular.dominio.builder;

import com.example.casapopular.dominio.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class PessoaBuilder {
    private String nome;
    private LocalDate dataDeNascimento;
    private BigDecimal renda;

    public Pessoa criar() {
        return new Pessoa(nome, dataDeNascimento, renda);
    }

    public PessoaBuilder() {
        nome = "Joviscleisson";
        dataDeNascimento = LocalDate.of(1996, 11, 28);
        renda = BigDecimal.valueOf(2000);
    }

    public PessoaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PessoaBuilder comDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public PessoaBuilder comIdade(Integer idade) {
        this.dataDeNascimento = LocalDate.now().minusYears(idade);
        return this;
    }

    public PessoaBuilder comRenda(Integer renda) {
        this.renda = BigDecimal.valueOf(renda);
        return this;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public Integer getIdade() {
        return Period.between(LocalDate.now(), dataDeNascimento).getYears();
    }
}
