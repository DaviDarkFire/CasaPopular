package com.example.casapopular.aplicacao;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.builder.FamiliaBuilder;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import com.example.casapopular.dominio.repositorio.ProcessoDeSelecaoRepositorio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SelecionaFamiliasTest {

    @MockBean
    private FamiliaRepositorio familiaRepositorio;
    @MockBean
    private ProcessoDeSelecaoRepositorio processoDeSelecaoRepositorio;
    @Autowired
    private SelecionaFamilias selecionaFamilias;
    private Integer quantidadeDeFamilias;

    @BeforeEach
    void setUp() {
        quantidadeDeFamilias = 15;
    }

    @Test
    void deveSelecionarFamilias() throws Exception {
        Familia primeiraFamilia = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        Familia segundaFamilia = new FamiliaBuilder().familiaComRendaAteMilEQuinhentosReais().criar();
        Familia terceiraFamilia = new FamiliaBuilder().familiaComUmOuDoisDependentes().criar();
        List<Familia> familias = Arrays.asList(primeiraFamilia, segundaFamilia, terceiraFamilia);
        Mockito.when(familiaRepositorio.findAll()).thenReturn(familias);

        ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);

        Assertions.assertThat(processoDeSelecaoDTO.familias).extracting(familiaSelecionadaDTO -> familiaSelecionadaDTO.id).
                containsExactlyElementsOf(familias.stream().map(Familia::getId).collect(Collectors.toList()));
    }

    @Test
    void deveSalvarProcessoDeSelecao() throws Exception{
        Familia familia = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        List<Familia> familias = Collections.singletonList(familia);
        ArgumentCaptor<ProcessoDeSelecao> capturadorDeArgumento = ArgumentCaptor.forClass(ProcessoDeSelecao.class);
        Mockito.when(familiaRepositorio.findAll()).thenReturn(familias);

        ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);

        Mockito.verify(processoDeSelecaoRepositorio).saveAndFlush(capturadorDeArgumento.capture());
        ProcessoDeSelecao processoCapturado = capturadorDeArgumento.getValue();
        Assertions.assertThat(processoCapturado.getId()).isEqualTo(processoDeSelecaoDTO.id);
    }

    @Test
    void deveLancarExcecaoCasoSejaInformadaUmaQuantidadeInvalidaDeFamiliasASeremSelecionadas() {
    }

    @Test
    void deveMapearCorretamenteOsDadosDeRetorno() {

    }
}