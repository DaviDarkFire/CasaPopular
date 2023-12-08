package com.example.casapopular.dominio;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

class PessoaTest {
    private String nome;
    private LocalDate dataDeNascimento;
    private Integer idade;
    private BigDecimal renda;

    @BeforeEach
    void setUp() {
        nome = "Vicente";
        dataDeNascimento = LocalDate.of(1996, 11, 28);
        idade = Period.between(dataDeNascimento, LocalDate.now()).getYears();
        renda = BigDecimal.valueOf(3000);
    }

    @Test
    void deveCriarPessoa() {
        Pessoa pessoa = new Pessoa(nome, dataDeNascimento, renda);

        Assertions.assertThat(pessoa.getIdade()).isEqualTo(idade);
        Assertions.assertThat(pessoa.getNome()).isEqualTo(nome);
        Assertions.assertThat(pessoa.getRenda()).isEqualTo(renda);
    }

    @Test
    void deveSerPossivelAlterarOsDadosDePessoa() {
        Pessoa pessoa = new Pessoa(nome, dataDeNascimento, renda);
        String novoNome = "Jão";
        BigDecimal novaRenda = BigDecimal.valueOf(7000);

        pessoa.setNome(novoNome);
        pessoa.setRenda(novaRenda);

        Assertions.assertThat(pessoa.getNome()).isEqualTo(novoNome);
        Assertions.assertThat(pessoa.getRenda()).isEqualTo(novaRenda);
    }
}