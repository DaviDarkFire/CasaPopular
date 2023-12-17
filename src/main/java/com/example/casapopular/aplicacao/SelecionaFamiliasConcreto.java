package com.example.casapopular.aplicacao;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.criterio.Criterio;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import com.example.casapopular.dominio.repositorio.ProcessoDeSelecaoRepositorio;
import com.example.casapopular.dominio.servicos.ServicoParaSelecionarFamilias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionaFamiliasConcreto implements SelecionaFamilias {
    private final List<Criterio> criterios;
    private final FamiliaRepositorio familiaRepositorio;
    private final ServicoParaSelecionarFamilias servicoParaSelecionarFamilias;
    private final ProcessoDeSelecaoRepositorio processoDeSelecaoRepositorio;

    @Autowired
    public SelecionaFamiliasConcreto(List<Criterio> criterios,
                                     FamiliaRepositorio familiaRepositorio,
                                     ServicoParaSelecionarFamilias servicoParaSelecionarFamilias,
                                     ProcessoDeSelecaoRepositorio processoDeSelecaoRepositorio) {
        this.criterios = criterios;
        this.familiaRepositorio = familiaRepositorio;
        this.servicoParaSelecionarFamilias = servicoParaSelecionarFamilias;
        this.processoDeSelecaoRepositorio = processoDeSelecaoRepositorio;
    }

    @Override
    public ProcessoDeSelecaoDTO executar(Integer quantidadeDeFamilias) {
        List<Familia> todasFamilias = familiaRepositorio.findAll();
        ProcessoDeSelecao processoDeSelecao = servicoParaSelecionarFamilias.selecionar(todasFamilias, criterios, quantidadeDeFamilias);
        processoDeSelecaoRepositorio.saveAndFlush(processoDeSelecao);
        return mapearProcessoDeSelecao(processoDeSelecao);
    }

    private ProcessoDeSelecaoDTO mapearProcessoDeSelecao(ProcessoDeSelecao processoDeSelecao) {
        ProcessoDeSelecaoDTO processoDeSelecaoDTO = new ProcessoDeSelecaoDTO();
        processoDeSelecaoDTO.id = processoDeSelecao.getId();
        processoDeSelecaoDTO.dataDeSelecao = processoDeSelecao.getDataDeSelecao();
        processoDeSelecaoDTO.familias = mapearFamiliasSelecionadas(processoDeSelecao.getFamiliasSelecionadas());
        return processoDeSelecaoDTO;
    }

    private List<FamiliaSelecionadaDTO> mapearFamiliasSelecionadas(List<FamiliaSelecionada> familiasSelecionadas) {
        return familiasSelecionadas.stream().map(familiaSelecionada -> {
            FamiliaSelecionadaDTO familiaSelecionadaDTO = new FamiliaSelecionadaDTO();
            familiaSelecionadaDTO.id = familiaSelecionada.getIdDaFamiliaSelecionada();
            familiaSelecionadaDTO.pontuacao = familiaSelecionada.getPontuacao();
            return familiaSelecionadaDTO;
        }).collect(Collectors.toList());
    }


}
