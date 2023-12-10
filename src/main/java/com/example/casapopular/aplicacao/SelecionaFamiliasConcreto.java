package com.example.casapopular.aplicacao;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.criterio.Criterio;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelecionaFamiliasConcreto implements SelecionaFamilias {

    private final List<Criterio> criterios;
    private final FamiliaRepositorio familiaRepositorio;

    @Autowired
    public SelecionaFamiliasConcreto(List<Criterio> criterios,
                                     FamiliaRepositorio familiaRepositorio) {
        this.criterios = criterios;
        this.familiaRepositorio = familiaRepositorio;
    }

    @Override
    public ProcessoDeSelecaoDTO executar(Integer quantidadeDeFamilias) {
        List<Familia> todasFamilias = familiaRepositorio.findAll();
//        list<PontuacaoDaFamilia> pontuacoes = servico()
        return null;
    }
}
