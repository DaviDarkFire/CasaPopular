package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import com.example.casapopular.dominio.builder.PessoaBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CriterioDeRendaTotalAteNovecentosReaisTest {

    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeRendaTotalAteNovecentosReais();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 899;
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(rendaQueNaoExcedeRendaDoCriterio).criar();
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteNovecentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaPossuaRendaQueEhOValorMaximoDoCriterio() {
        Integer rendaQueNaoExcedeRendaDoCriterio = 900;
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(rendaQueNaoExcedeRendaDoCriterio).criar();
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteNovecentosReais.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterio() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(899).criar();
        Pessoa outraPessoa = new PessoaBuilder().comIdade(34).comRenda(2).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, outraPessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeRendaTotalAteNovecentosReais.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}