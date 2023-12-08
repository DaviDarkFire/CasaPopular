package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import org.springframework.stereotype.Component;

@Component
public class CriterioDeQuantidadeDeDependentesMaiorIgualATres implements Criterio {

    private static final Integer QUANTIDADE_PADRAO_DEPENDENTES = 3;
    protected static final Integer PONTUACAO_ATENDE_CRITERIO = 3;
    protected static final Integer PONTUACAO_NAO_ATENDE_CRITERIO = 0;
    @Override
    public Integer pontuacao(Familia familia) {
        return atendeCriterio(familia) ? PONTUACAO_ATENDE_CRITERIO : PONTUACAO_NAO_ATENDE_CRITERIO;
    }

    private Boolean atendeCriterio(Familia familia) {
        return familia.quantidadeDeDependentes() >= QUANTIDADE_PADRAO_DEPENDENTES;
    }
}
