package com.example.casapopular.dominio;

import com.example.casapopular.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class FamiliaTest extends TestUtils {

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
}