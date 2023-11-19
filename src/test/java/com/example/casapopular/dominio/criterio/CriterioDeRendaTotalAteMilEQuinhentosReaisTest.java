package com.example.casapopular.dominio.criterio;

import com.example.casapopular.TestUtils;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CriterioDeRendaTotalAteMilEQuinhentosReaisTest extends TestUtils {

    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeRendaTotalAteMilEQuinhentosReais();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 1499;
        Pessoa pessoa = criarPessoa(34, rendaQueNaoExcedeRendaDoCriterio);
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComRendaUltrapassandoDoValorMaximo() {
        Pessoa pessoa = criarPessoa(34, 1499);
        Pessoa outraPessoa = criarPessoa(34, 2);
        Familia familia = new Familia(Arrays.asList(pessoa, outraPessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_NAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComRendaQueFiqueAbaixoDoValorMinimo() {
        Integer rendaAbaixoDoValorMinimo = 900;
        Pessoa pessoa = criarPessoa(34, rendaAbaixoDoValorMinimo);
        Familia familia = new Familia(Arrays.asList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}