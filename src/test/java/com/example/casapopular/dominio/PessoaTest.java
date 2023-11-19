package com.example.casapopular.dominio;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PessoaTest {
    private String nome;
    private Integer idade;
    private BigDecimal renda;

    @BeforeEach
    void setUp() {
        nome = "Vicente";
        idade = 56;
        renda = BigDecimal.valueOf(3000);
    }

    @Test
    void deveCriarPessoa() {
        Pessoa pessoa = new Pessoa(nome, idade, renda);

        Assertions.assertThat(pessoa.getIdade()).isEqualTo(idade);
        Assertions.assertThat(pessoa.getNome()).isEqualTo(nome);
        Assertions.assertThat(pessoa.getRenda()).isEqualTo(renda);
    }

    @Test
    void deveSerPossivelAlterarOsDadosDePessoa() {
        Pessoa pessoa = new Pessoa(nome, idade, renda);
        String novoNome = "Jão";
        Integer novaIdade = 23;
        BigDecimal novaRenda = BigDecimal.valueOf(7000);

        pessoa.setNome(novoNome);
        pessoa.setIdade(novaIdade);
        pessoa.setRenda(novaRenda);

        Assertions.assertThat(pessoa.getNome()).isEqualTo(novoNome);
        Assertions.assertThat(pessoa.getIdade()).isEqualTo(novaIdade);
        Assertions.assertThat(pessoa.getRenda()).isEqualTo(novaRenda);
    }
}