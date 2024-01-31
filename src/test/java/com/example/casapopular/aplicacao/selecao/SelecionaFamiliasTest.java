package com.example.casapopular.aplicacao.selecao;

import com.example.casapopular.dominio.Familia;
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

        Assertions.assertThat(processoDeSelecaoDTO.familias()).extracting(FamiliaSelecionadaDTO::id)
                .containsExactlyElementsOf(familias.stream().map(Familia::getId).collect(Collectors.toList()));
    }

    @Test
    void deveSalvarProcessoDeSelecao() throws Exception {
        Familia familia = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        List<Familia> familias = Collections.singletonList(familia);
        ArgumentCaptor<ProcessoDeSelecao> capturadorDeArgumento = ArgumentCaptor.forClass(ProcessoDeSelecao.class);
        Mockito.when(familiaRepositorio.findAll()).thenReturn(familias);

        ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);

        Mockito.verify(processoDeSelecaoRepositorio).saveAndFlush(capturadorDeArgumento.capture());
        ProcessoDeSelecao processoCapturado = capturadorDeArgumento.getValue();
        Assertions.assertThat(processoCapturado.getId()).isEqualTo(processoDeSelecaoDTO.id());
    }

    @Test
    void deveLancarExcecaoCasoSejaInformadaUmaQuantidadeNulaDeFamiliasASeremSelecionadas() {
        Throwable excecao = Assertions.catchThrowable(() -> selecionaFamilias.executar(null));

        Assertions.assertThat(excecao).isInstanceOf(Exception.class).hasMessageContaining(SelecionaFamiliasConcreto.MENSAGEM_ESPERADA_PARA_QUANTIDADE_DE_FAMILIAS_INVALIDA);
    }

    @Test
    void deveLancarExcecaoCasoSejaInformadaUmaQuantidadeNegativaDeFamiliasASeremSelecionadas() {
        Throwable excecao = Assertions.catchThrowable(() -> selecionaFamilias.executar(-1));

        Assertions.assertThat(excecao).isInstanceOf(Exception.class).hasMessageContaining(SelecionaFamiliasConcreto.MENSAGEM_ESPERADA_PARA_QUANTIDADE_DE_FAMILIAS_INVALIDA);
    }

    @Test
    void deveMapearCorretamenteOsDadosDeRetorno() throws Exception {
        Familia primeiraFamilia = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        List<Familia> familias = Collections.singletonList(primeiraFamilia);
        ArgumentCaptor<ProcessoDeSelecao> capturadorDeArgumento = ArgumentCaptor.forClass(ProcessoDeSelecao.class);
        Mockito.when(familiaRepositorio.findAll()).thenReturn(familias);
        Mockito.when(processoDeSelecaoRepositorio.saveAndFlush(capturadorDeArgumento.capture())).thenReturn(null);
        Integer pontuacaoEsperadaDaFamilia = 3;

        ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);

        ProcessoDeSelecao processoDeSelecao = capturadorDeArgumento.getValue();
        Assertions.assertThat(processoDeSelecaoDTO.id()).isEqualTo(processoDeSelecao.getId());
        Assertions.assertThat(processoDeSelecaoDTO.dataDeSelecao()).isEqualTo(processoDeSelecao.getDataDeSelecao());
        Assertions.assertThat(processoDeSelecaoDTO.familias()).extracting(FamiliaSelecionadaDTO::id)
                .containsExactlyElementsOf(familias.stream().map(Familia::getId).collect(Collectors.toList()));
        Assertions.assertThat(processoDeSelecaoDTO.familias()).extracting(FamiliaSelecionadaDTO::pontuacao)
                .containsExactly(pontuacaoEsperadaDaFamilia);
    }
}