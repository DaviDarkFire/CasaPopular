package com.example.casapopular.dominio.criterio;

import com.example.casapopular.TestUtils;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CriterioDeRendaTotalAteNovecentosReaisTest extends TestUtils {

    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeRendaTotalAteNovecentosReais();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 899;
        Pessoa pessoa = criarPessoa(34, rendaQueNaoExcedeRendaDoCriterio);
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteNovecentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterio() {
        Pessoa pessoa = criarPessoa(34, 899);
        Pessoa outraPessoa = criarPessoa(34, 2);
        Familia familia = new Familia(Arrays.asList(pessoa, outraPessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteNovecentosReais.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}