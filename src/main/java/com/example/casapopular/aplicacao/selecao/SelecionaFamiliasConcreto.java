package com.example.casapopular.aplicacao.selecao;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import com.example.casapopular.dominio.repositorio.ProcessoDeSelecaoRepositorio;
import com.example.casapopular.dominio.servicos.ServicoParaSelecionarFamilias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SelecionaFamiliasConcreto implements SelecionaFamilias {
    private final FamiliaRepositorio familiaRepositorio;
    private final ServicoParaSelecionarFamilias servicoParaSelecionarFamilias;
    private final ProcessoDeSelecaoRepositorio processoDeSelecaoRepositorio;
    protected static final String MENSAGEM_ESPERADA_PARA_QUANTIDADE_DE_FAMILIAS_INVALIDA = "Quantidade de famílias informadas inválida.";

    @Autowired
    public SelecionaFamiliasConcreto(FamiliaRepositorio familiaRepositorio,
                                     ServicoParaSelecionarFamilias servicoParaSelecionarFamilias,
                                     ProcessoDeSelecaoRepositorio processoDeSelecaoRepositorio) {
        this.familiaRepositorio = familiaRepositorio;
        this.servicoParaSelecionarFamilias = servicoParaSelecionarFamilias;
        this.processoDeSelecaoRepositorio = processoDeSelecaoRepositorio;
    }

    @Override
    public ProcessoDeSelecaoDTO executar(Integer quantidadeDeFamilias) throws Exception {
        validarQuantidadeDeFamilias(quantidadeDeFamilias);
        List<Familia> todasFamilias = familiaRepositorio.findAll();
        ProcessoDeSelecao processoDeSelecao = servicoParaSelecionarFamilias.selecionar(todasFamilias, quantidadeDeFamilias);
        processoDeSelecaoRepositorio.saveAndFlush(processoDeSelecao);
        return mapearProcessoDeSelecao(processoDeSelecao);
    }

    private void validarQuantidadeDeFamilias(Integer quantidadeDeFamilias) throws Exception {
        if (Objects.isNull(quantidadeDeFamilias) || quantidadeDeFamilias < 0) {
            throw new Exception(MENSAGEM_ESPERADA_PARA_QUANTIDADE_DE_FAMILIAS_INVALIDA);
        }
    }

    private ProcessoDeSelecaoDTO mapearProcessoDeSelecao(ProcessoDeSelecao processoDeSelecao) {
        return new ProcessoDeSelecaoDTO(processoDeSelecao.getId(),
                processoDeSelecao.getDataDeSelecao(),
                mapearFamiliasSelecionadas(processoDeSelecao.getFamiliasSelecionadas()));
    }

    private List<FamiliaSelecionadaDTO> mapearFamiliasSelecionadas(List<FamiliaSelecionada> familiasSelecionadas) {
        return familiasSelecionadas.stream()
                .map(familiaSelecionada ->
                        new FamiliaSelecionadaDTO(familiaSelecionada.getIdDaFamiliaSelecionada(), familiaSelecionada.getPontuacao()))
                .collect(Collectors.toList());
    }
}
