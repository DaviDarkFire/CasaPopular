package com.example.casapopular.aplicacao.comum;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ExcecaoDeCampoObrigatorioTest {

    @Test
    void deveLancarExcecaoCasoObjetoSejaNulo() {
        String mensagemEsperada = "Objeto nulo.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoNulo(null, mensagemEsperada)
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(ExcecaoDeCampoObrigatorio.class).hasMessageContaining(mensagemEsperada);
    }

    @Test
    void naoDeveLancarExcecaoCasoObjetoNaoSejaNulo() {

        Assertions.assertThatCode(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoNulo(new Object(), "")
                        .lancarExcecao()).doesNotThrowAnyException();
    }

    @Test
    void deveLancarExcecaoCasoColecaoSejaNula() {
        String mensagemEsperada = "Coleção nula.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoColecaoNula(null, mensagemEsperada)
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(ExcecaoDeCampoObrigatorio.class).hasMessageContaining(mensagemEsperada);
    }

    @Test
    void naoDeveLancarExcecaoCasoColecaoNaoSejaNula() {
        Assertions.assertThatCode(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoColecaoNula(new ArrayList(), "")
                        .lancarExcecao()).doesNotThrowAnyException();
    }

    @Test
    void deveLancarExcecaoCasoColecaoSejaVazia() {
        String mensagemEsperada = "Coleção vazia.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoColecaoVazia(new ArrayList(), mensagemEsperada)
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(ExcecaoDeCampoObrigatorio.class).hasMessageContaining(mensagemEsperada);
    }

    @Test
    void naoDeveLancarExcecaoCasoColecaoNaoSejaVazia() {
        Assertions.assertThatCode(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoColecaoVazia(List.of("tei"), "")
                        .lancarExcecao()).doesNotThrowAnyException();
    }

    @Test
    void deveRetornarTodasMensagensDeExcecaoEncontradasEmUmUnicoTexto() {
        String mensagemEsperada = "Objeto é nulo. Coleção Vazia.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new ExcecaoDeCampoObrigatorio()
                        .quandoNulo(null, "Objeto é nulo.")
                        .quandoColecaoVazia(new ArrayList(), "Coleção Vazia.")
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(ExcecaoDeCampoObrigatorio.class).hasMessageContaining(mensagemEsperada);
    }
}