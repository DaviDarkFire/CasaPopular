package com.example.casapopular.dominio;

import com.example.casapopular.dominio.builder.PessoaBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class FamiliaTest {

    @Test
    void deveCriarFamilia() {
        Pessoa pai = new PessoaBuilder().comIdade(51).comRenda(3000).criar();
        Pessoa mae = new PessoaBuilder().comIdade(51).comRenda(3000).criar();
        Pessoa filho = new PessoaBuilder().comIdade(17).criar();
        List<Pessoa> pessoas = Arrays.asList(pai, mae, filho);

        Familia familia = new Familia(pessoas);

        Assertions.assertThat(familia.getPessoas()).containsExactlyInAnyOrderElementsOf(pessoas);
    }

    @Test
    void deveCalcularRendaDaFamilia() {
        Pessoa pai = new PessoaBuilder().comIdade(51).comRenda(3000).criar();
        Pessoa mae = new PessoaBuilder().comIdade(51).comRenda(3000).criar();
        Pessoa filho = new PessoaBuilder().comIdade(17).criar();
        Familia familia = new Familia(Arrays.asList(pai, mae, filho));

        BigDecimal renda = familia.renda();

        Assertions.assertThat(renda).isEqualTo(BigDecimal.valueOf(6000));
    }

    @Test
    void deveRetornarQuantidadeDeDependentesDaFamilia() {
        Pessoa pai = new PessoaBuilder().comIdade(51).comRenda(3000).criar();
        Pessoa mae = new PessoaBuilder().comIdade(51).comRenda(3000).criar();
        Pessoa primeiroDePendente = new PessoaBuilder().comIdade(17).criar();
        Pessoa segundoDePendente = new PessoaBuilder().comIdade(10).criar();
        Pessoa terceiroDePendente = new PessoaBuilder().comIdade(2).criar();
        Familia familia = new Familia(Arrays.asList(pai, mae, primeiroDePendente, segundoDePendente, terceiroDePendente));

        Long quantidadeDeDependentes = familia.quantidadeDeDependentes();

        Assertions.assertThat(quantidadeDeDependentes).isEqualTo(3);
    }
}