package com.example.casapopular.dominio;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class FamiliaTest {

    @Test
    void deveCriarFamilia() {
        Pessoa pai = criarPessoa(51, 3000);
        Pessoa mae = criarPessoa(51, 3000);
        Pessoa filho = criarPessoa(17, 0);
        List<Pessoa> pessoas = Arrays.asList(pai, mae, filho);

        Familia familia = new Familia(pessoas);

        Assertions.assertThat(familia.getPessoas()).containsExactlyInAnyOrderElementsOf(pessoas);
    }

    @Test
    void deveAdicionarPessoaAFamilia() {
        Pessoa pai = criarPessoa(51, 3000);
        Pessoa mae = criarPessoa(51, 3000);
        Pessoa filho = criarPessoa(17, 0);
        Pessoa novoMembro = criarPessoa(13, 0);
        Familia familia = new Familia(Arrays.asList(pai, mae, filho));

        familia.adicionarPessoa(novoMembro);

        Assertions.assertThat(familia.getPessoas()).contains(novoMembro);
    }

    @Test
    void deveRemoverPessoaDaFamilia() {
        Pessoa pai = criarPessoa(51, 3000);
        Pessoa mae = criarPessoa(51, 3000);
        Pessoa membroASerRemovido = criarPessoa(17, 0);
        Familia familia = new Familia(Arrays.asList(pai, mae, membroASerRemovido));

        familia.removerPessoa(membroASerRemovido);

        Assertions.assertThat(familia.getPessoas()).doesNotContain(membroASerRemovido);
    }

    @Test
    void deveCalcularRendaDaFamilia() {
        Pessoa pai = criarPessoa(51, 3000);
        Pessoa mae = criarPessoa(51, 3000);
        Pessoa filho = criarPessoa(17, 0);
        Familia familia = new Familia(Arrays.asList(pai, mae, filho));

        BigDecimal renda = familia.renda();

        Assertions.assertThat(renda).isEqualTo(BigDecimal.valueOf(6000));
    }

    @Test
    void deveRetornarQuantidadeDeDependentesDaFamilia() {
        Pessoa pai = criarPessoa(51, 3000);
        Pessoa mae = criarPessoa(51, 3000);
        Pessoa primeiroDePendente = criarPessoa(17, 0);
        Pessoa segundoDePendente = criarPessoa(10, 0);
        Pessoa terceiroDePendente = criarPessoa(2, 0);
        Familia familia = new Familia(Arrays.asList(pai, mae, primeiroDePendente, segundoDePendente, terceiroDePendente));

        Long quantidadeDeDependentes = familia.quantidadeDeDependentes();

        Assertions.assertThat(quantidadeDeDependentes).isEqualTo(3);
    }

    private Pessoa criarPessoa(Integer idade, Integer renda) {
        String nomeAleatorio = UUID.randomUUID().toString();
        return new Pessoa(nomeAleatorio, idade, BigDecimal.valueOf(renda));
    }
}