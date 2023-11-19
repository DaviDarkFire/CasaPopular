package com.example.casapopular.dominio.criterio;

import com.example.casapopular.TestUtils;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CriterioDeQuantidadeDeDependentesMaiorIgualATresTest extends TestUtils {

    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeQuantidadeDeDependentesMaiorIgualATres();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterio() {
        Pessoa pessoa = criarPessoa(34, 900);
        Pessoa primeiroDependente = criarPessoa(17, 0);
        Pessoa segundoDependente = criarPessoa(12, 0);
        Pessoa terceiroDependente = criarPessoa(12, 0);
        Pessoa quartoDependente = criarPessoa(9, 0);
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente, terceiroDependente, quartoDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesMaiorIgualATres.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterio() {
        Pessoa pessoa = criarPessoa(34, 900);
        Pessoa primeiroDependente = criarPessoa(17, 0);
        Pessoa segundoDependente = criarPessoa(12, 0);
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesMaiorIgualATres.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}