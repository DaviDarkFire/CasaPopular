package com.example.casapopular.dominio.servicos;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.criterio.Criterio;

import java.util.Comparator;
import java.util.List;

public class ServicoParaPontuarFamilias {

    public ProcessoDeSelecao pontuar(List<Familia> familias, List<Criterio> criterios) {
        List<FamiliaSelecionada> familiasPontuadas = familias.stream()
                .map(familia -> {
                    Integer somaDosPontos = criterios.stream().map(criterio -> criterio.pontuacao(familia)).reduce(0, Integer::sum);
                    return new FamiliaSelecionada(familia.getId(), somaDosPontos);
                })
                .sorted(Comparator.comparing(FamiliaSelecionada::getPontuacao).reversed())
                .toList();
        return new ProcessoDeSelecao(familiasPontuadas);
    }
}
