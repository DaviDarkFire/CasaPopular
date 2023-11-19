package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CriterioDeRendaTotalAteMilEQuinhentosReais implements Criterio {

    protected static final BigDecimal RENDA_MAXIMA_DO_CRITERIO = BigDecimal.valueOf(1500);
    protected static final BigDecimal RENDA_MINIMA_DO_CRITERIO = BigDecimal.valueOf(901);
    protected static final Integer PONTUACAO_ATENDE_CRITERIO = 3;
    protected static final Integer PONTUACAO_NAO_ATENDE_CRITERIO = 0;
    @Override
    public Integer pontuacao(Familia familia) {
        return atendeCriterio(familia) ? PONTUACAO_ATENDE_CRITERIO : PONTUACAO_NAO_ATENDE_CRITERIO;
    }

    @Override
    public Boolean atendeCriterio(Familia familia) {
        return familia.renda().compareTo(RENDA_MINIMA_DO_CRITERIO) >= 0 &&
                familia.renda().compareTo(RENDA_MAXIMA_DO_CRITERIO) <= 0;
    }
}
