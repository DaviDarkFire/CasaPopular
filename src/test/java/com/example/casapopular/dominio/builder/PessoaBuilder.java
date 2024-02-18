package com.example.casapopular.dominio.builder;

import com.example.casapopular.dominio.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class PessoaBuilder {
    private String nome;
    private LocalDate dataDeNascimento;
    private BigDecimal renda;

    public Pessoa criar() {
        return new Pessoa(nome, dataDeNascimento, renda);
    }

    public PessoaBuilder() {
        nome = "Joviscleisson" + obterNumeroAleatorio(10);
        this.comIdade(obterNumeroAleatorio(45));
        this.comRenda(obterNumeroAleatorio(3500));
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

    private Integer obterNumeroAleatorio(int numero) {
        Random random = new Random();
        return random.nextInt(numero);
    }
}
