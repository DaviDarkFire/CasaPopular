package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.lang.model.util.Types;

import static org.junit.jupiter.api.Assertions.*;

public class AdicionaFamiliaTest {

    private FamiliaRepositorio familiaRepositorio;
    private AdicionaFamilia adicionaFamilia;

    @BeforeEach
    void setUp() {
        familiaRepositorio = Mockito.mock(FamiliaRepositorio.class);
        adicionaFamilia = new AdicionaFamiliaConcreto(familiaRepositorio);
    }

    @Test
    void deveAdicionarFamilia() {


        ArgumentCaptor<Familia> argumentCaptor = ArgumentCaptor.forClass(Familia.class);
        Mockito.verify(familiaRepositorio).save(argumentCaptor.capture());
        Familia familiaCapturada = argumentCaptor.getValue();

    }

    @Test
    void naoDeveAdicionarFamiliaCasoListaDePessoasVazia() {

    }

    @Test
    void naoDeveAdicionarFamiliaCasoListaDePessoasNula() {

    }
}