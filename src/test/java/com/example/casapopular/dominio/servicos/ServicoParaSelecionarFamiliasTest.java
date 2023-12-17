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
class ServicoParaSelecionarFamiliasTest {

    @Autowired
    private List<Criterio> criterios;

    private ServicoParaSelecionarFamilias servico;

    @BeforeEach
    void setUp() {
        servico = new ServicoParaSelecionarFamilias();
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
        Integer quantidadeEsperadaDeFamilias = 4;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, criterios, quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
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
        Integer quantidadeEsperadaDeFamilias = 1;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, criterios, quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsOnly(idEsperadoDaFamilia);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(pontuacaoEsperada);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveRetornarProcessoDeSelecaoComFamiliasSelecionadasAPartirDeApenasUmCriterio() {
        Familia familiaComRendaAteNovecentosReais = new FamiliaBuilder()
                .comPessoas(new PessoaBuilder().comRenda(900).criar())
                .criar();
        TesteUtils.alterarCampoPorReflection("id", Familia.class, familiaComRendaAteNovecentosReais, 1L);
        List<Familia> familias = Collections.singletonList(familiaComRendaAteNovecentosReais);
        Long idEsperadoDaFamilia = familias.get(0).getId();
        criterios = Collections.singletonList(new CriterioDeRendaTotalAteNovecentosReais());
        int pontuacaoEsperada = 5;
        Integer quantidadeEsperadaDeFamilias = 1;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, criterios, 1);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsOnly(idEsperadoDaFamilia);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(pontuacaoEsperada);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }

    @Test
    void deveRetornarUmProcessoDeSelecaoComUmaListaDeFamiliasSelecionadasVaziaCasoNaoSejamInformadasFamilias() {
        ProcessoDeSelecao processoDeSelecao = servico.selecionar(Collections.emptyList(), criterios, 10);

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
        Integer quantidadeEsperadaDeFamilias = 2;

        ProcessoDeSelecao processoDeSelecao = servico.selecionar(familias, criterios,quantidadeEsperadaDeFamilias);

        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getIdDaFamiliaSelecionada)
                .containsExactlyElementsOf(idsEsperadosDasFamilas);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas()).extracting(FamiliaSelecionada::getPontuacao)
                .containsOnly(valorEsperado);
        Assertions.assertThat(processoDeSelecao.getFamiliasSelecionadas().size()).isEqualTo(quantidadeEsperadaDeFamilias);
    }
}