package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.aplicacao.comum.ExcecaoDeCampoObrigatorio;
import com.example.casapopular.aplicacao.selecao.PessoaDTO;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import com.example.casapopular.dominio.builder.PessoaBuilder;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;

public class AdicionaFamiliaTest {

    private FamiliaRepositorio familiaRepositorio;
    private AdicionaFamilia adicionaFamilia;

    @BeforeEach
    void setUp() {
        familiaRepositorio = Mockito.mock(FamiliaRepositorio.class);
        adicionaFamilia = new AdicionaFamiliaConcreto(familiaRepositorio);
    }

    @Test
    void deveAdicionarFamilia() throws Exception {
        AdicionaFamiliaComando comando = inicializarComando();

        adicionaFamilia.executar(comando);

        ArgumentCaptor<Familia> argumentCaptor = ArgumentCaptor.forClass(Familia.class);
        Mockito.verify(familiaRepositorio).save(argumentCaptor.capture());
        Familia familiaCapturada = argumentCaptor.getValue();
        Assertions.assertThat(familiaPossuiPessoas(familiaCapturada, comando.getPessoas())).isTrue();
    }

    @Test
    void naoDeveAdicionarFamiliaCasoListaDePessoasVazia() {
        AdicionaFamiliaComando comando = new AdicionaFamiliaComando(new ArrayList<>(), Optional.empty());

        Throwable excecao = Assertions.catchThrowable(() -> adicionaFamilia.executar(comando));

        Assertions.assertThat(excecao).isInstanceOf(ExcecaoDeCampoObrigatorio.class).hasMessageContaining(AdicionaFamiliaConcreto.MENSAGEM_ESPERADA_PARA_COMANDO_INVALIDO);
    }

    @Test
    void naoDeveAdicionarFamiliaCasoListaDePessoasNula() {
        AdicionaFamiliaComando comando = new AdicionaFamiliaComando(null, Optional.empty());

        Throwable excecao = Assertions.catchThrowable(() -> adicionaFamilia.executar(comando));

        Assertions.assertThat(excecao).isInstanceOf(ExcecaoDeCampoObrigatorio.class).hasMessageContaining(AdicionaFamiliaConcreto.MENSAGEM_ESPERADA_PARA_COMANDO_INVALIDO);
    }

    private AdicionaFamiliaComando inicializarComando() throws Exception {
        List<PessoaDTO> pessoas = criarListaDePessoas();
        return new AdicionaFamiliaComando(pessoas, Optional.empty());
    }

    private List<PessoaDTO> criarListaDePessoas() {
        return Arrays.asList(criarPessoaDTO(), criarPessoaDTO(), criarPessoaDTO());
    }

    private PessoaDTO criarPessoaDTO() {
        return mapearPessoa(new PessoaBuilder().criar());
    }

    private PessoaDTO mapearPessoa(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getNome(), pessoa.getDataDeNascimento(), pessoa.getRenda());
    }

    private boolean familiaPossuiPessoas(Familia familia, List<PessoaDTO> pessoasDTO) {
        return pessoasDTO.stream().allMatch(pessoaDTO -> pessoaEstaNaFamilia(pessoaDTO, familia));
    }

    private boolean pessoaEstaNaFamilia(PessoaDTO pessoaDTO, Familia familia) {
        return familia.getPessoas().stream().anyMatch(pessoa -> pessoasSaoIguais(pessoaDTO, pessoa));
    }

    private static boolean pessoasSaoIguais(PessoaDTO pessoaDTO, Pessoa pessoa) {
        return Objects.equals(pessoa.getNome(), pessoaDTO.nome()) &&
                Objects.equals(pessoa.getDataDeNascimento(), pessoaDTO.dataDeNascimento()) &&
                Objects.equals(pessoa.getRenda(), pessoaDTO.renda());
    }
}