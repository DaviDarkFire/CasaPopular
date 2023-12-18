package com.example.casapopular.dominio.servicos;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.FamiliaSelecionada;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.criterio.Criterio;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ServicoParaSelecionarFamilias {

    //TODO: injetar critérios

    public ProcessoDeSelecao selecionar(List<Familia> familias, List<Criterio> criterios, Integer quantidadeDeFamilias) {
        List<FamiliaSelecionada> familiasSelecionadas = familias.stream()
                .map(familia -> {
                    Integer somaDosPontos = criterios.stream().map(criterio -> criterio.pontuacao(familia)).reduce(0, Integer::sum);
                    return new FamiliaSelecionada(familia.getId(), somaDosPontos);
                })
                .sorted(Comparator.comparing(FamiliaSelecionada::getPontuacao).reversed())
                .limit(quantidadeDeFamilias)
                .toList();
        return new ProcessoDeSelecao(familiasSelecionadas);
    }
}
