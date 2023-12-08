package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import com.example.casapopular.dominio.builder.PessoaBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CriterioDeRendaTotalAteMilEQuinhentosReaisTest {

    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeRendaTotalAteMilEQuinhentosReais();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 1499;
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(rendaQueNaoExcedeRendaDoCriterio).criar();
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaTenhaARendaMaximaQueAtendeCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 1500;
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(rendaQueNaoExcedeRendaDoCriterio).criar();
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaTenhaARendaMinimaQueAtendeCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 901;
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(rendaQueNaoExcedeRendaDoCriterio).criar();
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComRendaUltrapassandoDoValorMaximo() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(1499).criar();
        Pessoa outraPessoa = new PessoaBuilder().comIdade(34).comRenda(2).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, outraPessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_NAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComRendaQueFiqueAbaixoDoValorMinimo() {
        Integer rendaAbaixoDoValorMinimo = 900;
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(rendaAbaixoDoValorMinimo).criar();
        Familia familia = new Familia(Arrays.asList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteMilEQuinhentosReais.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}