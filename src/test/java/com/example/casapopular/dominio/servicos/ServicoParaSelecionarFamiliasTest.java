package com.example.casapopular.dominio.servicos;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.builder.FamiliaBuilder;
import com.example.casapopular.dominio.criterio.Criterio;
import com.example.casapopular.dominio.criterio.CriterioDeRendaTotalAteNovecentosReais;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ServicoParaSelecionarFamiliasTest {

    @Autowired
    private List<Criterio> criterios;

    private ServicoParaSelecionarFamilias servico;

    @BeforeEach
    void setUp() {
        servico = new ServicoParaSelecionarFamilias(criterios);
    }

    @Test
    void devePontuarFamiliasEmOrdemCrescentePorPontuacao() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder().familiaComRendaAteNovecentosReais().criar();
        Familia familiaComRendaAteMilEQuinhetosReais = new FamiliaBuilder().familiaComRendaAteMilEQuinhentosReais().criar();
        Familia familiaComMaisDeTresDependentes = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        Familia familiaQuePossuiDeUmADoisDependentes = new FamiliaBuilder().familiaComUmOuDoisDependentes().criar();
        List<Familia> familias = Arrays.asList(familiaComRendaAteNovecentosReais,
                familiaComRendaAteMilEQuinhetosReais,
                familiaComMaisDeTresDependentes,
                familiaQuePossuiDeUmADoisDependentes);
        List<Long> idsEsperadosDasFamilas = familias.stream().map(Familia::getId).collect(Collectors.toList());
        Integer quantidadeEsperadaDeFamilias = 4;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveRetornarProcessoDeSelecaoComApenasUmFamiliaCasoHajaApenasUmFamilia() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder().familiaComRendaAteNovecentosReais().criar();
        List<Familia> familias = Collections.singletonList(familiaComRendaAteNovecentosReais);
        Long idEsperadoDaFamilia = familias.get(0).getId();
        int pontuacaoEsperada = 5;
        Integer quantidadeEsperadaDeFamilias = 1;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsOnly(idEsperadoDaFamilia);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(pontuacaoEsperada);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveRetornarProcessoDeSelecaoComFamiliasSelecionadasAPartirDeApenasUmCriterio() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder().familiaComRendaAteNovecentosReais().criar();
        List<Familia> familias = Collections.singletonList(familiaComRendaAteNovecentosReais);
        Long idEsperadoDaFamilia = familias.get(0).getId();
        criterios = Collections.singletonList(new CriterioDeRendaTotalAteNovecentosReais());
        int pontuacaoEsperada = 5;
        Integer quantidadeEsperadaDeFamilias = 1;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, 1);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsOnly(idEsperadoDaFamilia);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(pontuacaoEsperada);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveRetornarUmProcessoDeSelecaoComUmaListaDeFamiliasSelecionadasVaziaCasoNaoSejamInformadasFamilias() {
        ProcessoDeSelecao processoDeSelecao = servico.selecionar(Collections.emptyList(), 10);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).isEmpty();
    }

    @Test
    void deveRetornarUmProcessoDeSelecaoComUmaListaDeFamiliasSelecionadasComPontuacaoZeradaCasoNaoSejamInformadosCriterios() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder().familiaComRendaAteNovecentosReais().criar();
        Familia familiaComRendaAteMilEQuinhetosReais = new FamiliaBuilder().familiaComRendaAteMilEQuinhentosReais().criar();
        List<Familia> familias = Arrays.asList(familiaComRendaAteNovecentosReais, familiaComRendaAteMilEQuinhetosReais);
        criterios = Collections.emptyList();
        servico = new ServicoParaSelecionarFamilias(criterios);
        List<Long> idsEsperadosDasFamilas = familias.stream().map(Familia::getId).collect(Collectors.toList());
        int valorEsperado = 0;
        Integer quantidadeEsperadaDeFamilias = 2;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(valorEsperado);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveSelecionarSomenteAQuantidadeDeFamiliasInformadaBaseadoNaPontuacao() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder().familiaComRendaAteNovecentosReais().criar();
        Familia familiaComRendaAteMilEQuinhetosReais = new FamiliaBuilder().familiaComRendaAteMilEQuinhentosReais().criar();
        Familia familiaComMaisDeTresDependentes = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        Familia familiaQuePossuiDeUmADoisDependentes = new FamiliaBuilder().familiaComUmOuDoisDependentes().criar();
        List<Familia> familias = Arrays.asList(familiaComRendaAteNovecentosReais,
                familiaComRendaAteMilEQuinhetosReais,
                familiaComMaisDeTresDependentes,
                familiaQuePossuiDeUmADoisDependentes);
        Integer quantidadeEsperadaDeFamilias = 2;
        List<Long> idsEsperadosDasFamilas = familias.stream().map(Familia::getId).limit(quantidadeEsperadaDeFamilias).collect(Collectors.toList());

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveRetornarProcessoDeSelecaoComTodasAsFamiliasOrdenadasPorPontuacaoCasoAQuantidadeDeFamiliasSolicitadasSejaMaiorQueOTotalDeFamiliasExistentes() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder().familiaComRendaAteNovecentosReais().criar();
        Familia familiaComRendaAteMilEQuinhetosReais = new FamiliaBuilder().familiaComRendaAteMilEQuinhentosReais().criar();
        Familia familiaComMaisDeTresDependentes = new FamiliaBuilder().familiaComMaisDeTresDependentes().criar();
        Familia familiaQuePossuiDeUmADoisDependentes = new FamiliaBuilder().familiaComUmOuDoisDependentes().criar();
        List<Familia> familias = Arrays.asList(familiaComRendaAteNovecentosReais,
                familiaComRendaAteMilEQuinhetosReais,
                familiaComMaisDeTresDependentes,
                familiaQuePossuiDeUmADoisDependentes);
        Integer quantidadeEsperadaDeFamiliasMaiorQueQuantidadeDeFamiliasExistentes = 5;
        List<Long> idsEsperadosDasFamilas = familias.stream().map(Familia::getId).limit(quantidadeEsperadaDeFamiliasMaiorQueQuantidadeDeFamiliasExistentes)
                .collect(Collectors.toList());

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, quantidadeEsperadaDeFamiliasMaiorQueQuantidadeDeFamiliasExistentes);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
    }
}