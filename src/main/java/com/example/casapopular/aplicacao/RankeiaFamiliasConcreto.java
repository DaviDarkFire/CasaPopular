package com.example.casapopular.aplicacao;

import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.ProcessoDeSelecao;
import com.example.casapopular.dominio.criterio.Criterio;
import com.example.casapopular.repositorio.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankeiaFamiliasConcreto implements RankeiaFamilias{

    private final List<Criterio> criterios;
    private final FamiliaRepository familiaRepository;

    @Autowired
    public RankeiaFamiliasConcreto(List<Criterio> criterios,
                                   FamiliaRepository familiaRepository) {
        this.criterios = criterios;
        this.familiaRepository = familiaRepository;
    }

    @Override
    public ProcessoDeSelecao executar(Integer quantidadeDeFamilias) {
        List<Familia> todasFamilias = familiaRepository.findAll();
//        list<PontuacaoDaFamilia> pontuacoes = servico()
        return null;
    }
}
