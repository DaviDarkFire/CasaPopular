package com.example.casapopular.aplicacao.comum;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ExcecaoTest {

    @Test
    void deveLancarExcecaoCasoObjetoSejaNulo() {
        String mensagemEsperada = "Objeto nulo.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new Excecao()
                        .quandoNulo(null, mensagemEsperada)
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(Exception.class).hasMessageContaining(mensagemEsperada);
    }

    @Test
    void naoDeveLancarExcecaoCasoObjetoNaoSejaNulo() {

        Assertions.assertThatCode(() ->
                new Excecao()
                        .quandoNulo(new Object(), "")
                        .lancarExcecao()).doesNotThrowAnyException();
    }

    @Test
    void deveLancarExcecaoCasoColecaoSejaNula() {
        String mensagemEsperada = "Coleção nula.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new Excecao()
                        .quandoColecaoNula(null, mensagemEsperada)
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(Exception.class).hasMessageContaining(mensagemEsperada);
    }

    @Test
    void naoDeveLancarExcecaoCasoColecaoNaoSejaNula() {
        Assertions.assertThatCode(() ->
                new Excecao()
                        .quandoColecaoNula(new ArrayList(), "")
                        .lancarExcecao()).doesNotThrowAnyException();
    }

    @Test
    void deveLancarExcecaoCasoColecaoSejaVazia() {
        String mensagemEsperada = "Coleção vazia.";

        Throwable excecao = Assertions.catchThrowable(() ->
                new Excecao()
                        .quandoColecaoVazia(new ArrayList(), mensagemEsperada)
                        .lancarExcecao());

        Assertions.assertThat(excecao).isInstanceOf(Exception.class).hasMessageContaining(mensagemEsperada);
    }

    @Test
    void naoDeveLancarExcecaoCasoColecaoNaoSejaVazia() {
        Assertions.assertThatCode(() ->
                new Excecao()
                        .quandoColecaoVazia(List.of("tei"), "")
                        .lancarExcecao()).doesNotThrowAnyException();
    }

    @Test
    void deveLancarExcecaoCasoColecaoSejaNulaEVazia() {

    }

    @Test
    void naoDeveLancarExcecaoCasoColecaoNaoSejaNulaNemVazia() {

    }
}