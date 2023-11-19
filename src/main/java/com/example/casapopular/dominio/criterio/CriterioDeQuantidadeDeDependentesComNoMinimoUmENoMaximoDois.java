package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import org.springframework.stereotype.Component;

@Component
public class CriterioDeQuantidadeDeDependentesComNoMinimoUmENoMaximoDois implements Criterio {
    protected static final Integer QUANTIDADE_MINIMA_DEPENDENTES = 1;
    protected static final Integer QUANTIDADE_MAXIMA_DEPENDENTES = 2;
    protected static final Integer PONTUACAO_ATENDE_CRITERIO = 2;
    protected static final Integer PONTUACAO_NAO_ATENDE_CRITERIO = 0;
    @Override
    public Integer pontuacao(Familia familia) {
        return atendeCriterio(familia) ? PONTUACAO_ATENDE_CRITERIO : PONTUACAO_NAO_ATENDE_CRITERIO;
    }

    @Override
    public Boolean atendeCriterio(Familia familia) {
        return familia.quantidadeDeDependentes() >= QUANTIDADE_MINIMA_DEPENDENTES &&
                familia.quantidadeDeDependentes() <= QUANTIDADE_MAXIMA_DEPENDENTES;
    }
}
