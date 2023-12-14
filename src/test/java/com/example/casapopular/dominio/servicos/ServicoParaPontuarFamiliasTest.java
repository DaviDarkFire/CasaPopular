package com.example.casapopular.dominio.servicos;

import com.example.casapopular.TesteUtils;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.builder.FamiliaBuilder;
import com.example.casapopular.dominio.builder.PessoaBuilder;
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
class ServicoParaPontuarFamiliasTest {

    @Autowired
    private List<Criterio> criterios;

    private ServicoParaPontuarFamilias servico;

    @BeforeEach
    void setUp() {
        servico = new ServicoParaPontuarFamilias();
    }

    @Test
    void devePontuarFamiliasEmOrdemCrescentePorPontuacao() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(900).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteNovecentosReais, 1L);
        Familia familiaComRendaAteMilEQuinhetosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(1500).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteMilEQuinhetosReais, 2L);
        Familia familiaComMaisDeTresDependentes = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comIdade(10).criar(),
                        new PessoaBuilder().comIdade(11).criar(),
                        new PessoaBuilder().comIdade(12).criar(),
                        new PessoaBuilder().comIdade(13).criar(),
                        new PessoaBuilder().comIdade(18).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComMaisDeTresDependentes, 3L);
        Familia familiaQuePossuiDeUmADoisDependentes = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comIdade(10).criar(),
                        new PessoaBuilder().comIdade(11).criar(),
                        new PessoaBuilder().comIdade(18).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaQuePossuiDeUmADoisDependentes, 4L);
        List<Familia> familias = Arrays.asList(familiaComRendaAteNovecentosReais,
                familiaComRendaAteMilEQuinhetosReais,
                familiaComMaisDeTresDependentes,
                familiaQuePossuiDeUmADoisDependentes);
        List<Long> idsEsperadosDasFamilas = familias.stream().map(Familia::getId).collect(Collectors.toList());

        ProcessoDeSelecao processoDeSelecao = servico.pontuar(familias, criterios);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
    }

    @Test
    void devePontuarApenasUmFamilia() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(900).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteNovecentosReais, 1L);
        List<Familia> familias = Collections.singletonList(familiaComRendaAteNovecentosReais);
        Long idEsperadoDaFamilia = familias.get(0).getId();
        int pontuacaoEsperada = 5;

        ProcessoDeSelecao processoDeSelecao = servico.pontuar(familias, criterios);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsOnly(idEsperadoDaFamilia);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(pontuacaoEsperada);
    }

    @Test
    void devePontuarFamiliasComApenasUmCriterio() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(900).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteNovecentosReais, 1L);
        List<Familia> familias = Collections.singletonList(familiaComRendaAteNovecentosReais);
        Long idEsperadoDaFamilia = familias.get(0).getId();
        criterios = Collections.singletonList(new CriterioDeRendaTotalAteNovecentosReais());
        int pontuacaoEsperada = 5;

        ProcessoDeSelecao processoDeSelecao = servico.pontuar(familias, criterios);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsOnly(idEsperadoDaFamilia);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(pontuacaoEsperada);
    }

    @Test
    void deveRetornarUmProcessoDeSelecaoComUmaListaDeFamiliasSelecionadasVaziaCasoNaoSejamInformadasFamilias() {
        ProcessoDeSelecao processoDeSelecao = servico.pontuar(Collections.emptyList(), criterios);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).isEmpty();
    }

    @Test
    void deveRetornarUmProcessoDeSelecaoComUmaListaDeFamiliasSelecionadasComPontuacaoZeradaCasoNaoSejamInformadosCriterios() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(900).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteNovecentosReais, 1L);
        Familia familiaComRendaAteMilEQuinhetosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(1500).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteMilEQuinhetosReais, 2L);
        List<Familia> familias = Arrays.asList(familiaComRendaAteNovecentosReais, familiaComRendaAteMilEQuinhetosReais);
        criterios = Collections.emptyList();
        List<Long> idsEsperadosDasFamilas = familias.stream().map(Familia::getId).collect(Collectors.toList());
        int valorEsperado = 0;

        ProcessoDeSelecao processoDeSelecao = servico.pontuar(familias, criterios);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(valorEsperado);
    }
}