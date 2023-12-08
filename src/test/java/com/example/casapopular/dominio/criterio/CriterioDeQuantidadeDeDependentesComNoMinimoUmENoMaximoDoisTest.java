package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import com.example.casapopular.dominio.builder.PessoaBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDoisTest {
    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterioTendoOMaximoDeDependentes() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Pessoa primeiroDependente = new PessoaBuilder().comIdade(17).criar();
        Pessoa segundoDependente = new PessoaBuilder().comIdade(17).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterioTendoOMinimoDeDependentes() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Pessoa primeiroDependente = new PessoaBuilder().comIdade(17).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComMaisDependentesDoQueOMaximoDoCriterio() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Pessoa primeiroDependente = new PessoaBuilder().comIdade(17).criar();
        Pessoa segundoDependente = new PessoaBuilder().comIdade(12).criar();
        Pessoa terceiroDependente = new PessoaBuilder().comIdade(12).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente, terceiroDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_NAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterioComMenosDependentesDoQueOMinimoDoCriterio() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Familia familia = new Familia(Collections.singletonList(pessoa));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}