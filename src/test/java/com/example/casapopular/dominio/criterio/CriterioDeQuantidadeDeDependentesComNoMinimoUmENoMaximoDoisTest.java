package com.example.casapopular.dominio.criterio;

import com.example.casapopular.TestUtils;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDoisTest extends TestUtils {
    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterioTendoOMaximoDeDependentes() {
        Pessoa pessoa = criarPessoa(34, 900);
        Pessoa primeiroDependente = criarPessoa(17, 0);
        Pessoa segundoDependente = criarPessoa(12, 0);
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterioTendoOMinimoDeDependentes() {
        Pessoa pessoa = criarPessoa(34, 900);
        Pessoa primeiroDependente = criarPessoa(17, 0);
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComMaisDependentesDoQueOMaximoDoCriterio() {
        Pessoa pessoa = criarPessoa(34, 900);
        Pessoa primeiroDependente = criarPessoa(17, 0);
        Pessoa segundoDependente = criarPessoa(12, 0);
        Pessoa terceiroDependente = criarPessoa(12, 0);
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente, terceiroDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_NAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComMenosDependentesDoQueOMinimoDoCriterio() {
        Pessoa pessoa = criarPessoa(34, 900);
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}