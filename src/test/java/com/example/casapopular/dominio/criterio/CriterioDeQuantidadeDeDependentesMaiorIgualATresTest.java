package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import com.example.casapopular.dominio.builder.PessoaBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CriterioDeQuantidadeDeDependentesMaiorIgualATresTest {

    private Criterio criterio;

    @BeforeEach
    void setUp() {
        criterio = new CriterioDeQuantidadeDeDependentesMaiorIgualATres();
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterio() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Pessoa primeiroDependente = new PessoaBuilder().comIdade(17).criar();
        Pessoa segundoDependente = new PessoaBuilder().comIdade(12).criar();
        Pessoa terceiroDependente = new PessoaBuilder().comIdade(12).criar();
        Pessoa quartoDependente = new PessoaBuilder().comIdade(9).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente, terceiroDependente, quartoDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesMaiorIgualATres.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoDoCriterioCasoFamiliaAtendaCriterioTendoOMinimoDeDependentesDoCriterio() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Pessoa primeiroDependente = new PessoaBuilder().comIdade(17).criar();
        Pessoa segundoDependente = new PessoaBuilder().comIdade(12).criar();
        Pessoa terceiroDependente = new PessoaBuilder().comIdade(12).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente, terceiroDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesMaiorIgualATres.PONTUACAO_ATENDE_CRITERIO);
    }

    @Test
    void deveRetornarPontuacaoZeroCasoFamiliaNaoAtendaCriterio() {
        Pessoa pessoa = new PessoaBuilder().comIdade(34).comRenda(900).criar();
        Pessoa primeiroDependente = new PessoaBuilder().comIdade(17).criar();
        Pessoa segundoDependente = new PessoaBuilder().comIdade(12).criar();
        Familia familia = new Familia(Arrays.asList(pessoa, primeiroDependente, segundoDependente));

        Integer pontuacao = criterio.pontuacao(familia);

        Assertions.assertThat(pontuacao).isEqualTo(CriterioDeQuantidadeDeDependentesMaiorIgualATres.PONTUACAO_NAO_ATENDE_CRITERIO);
    }
}