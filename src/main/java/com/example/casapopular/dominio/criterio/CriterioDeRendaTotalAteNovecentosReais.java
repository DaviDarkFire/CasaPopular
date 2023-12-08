package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CriterioDeRendaTotalAteNovecentosReais implements Criterio{

    private static final BigDecimal RENDA_PADRAO_DO_CRITERIO = BigDecimal.valueOf(900);
    protected static final Integer PONTUACAO_ATENDE_CRITERIO = 5;
    protected static final Integer PONTUACAO_NAO_ATENDE_CRITERIO = 0;
    @Override
    public Integer pontuacao(Familia familia) {
        return atendeCriterio(familia) ? PONTUACAO_ATENDE_CRITERIO : PONTUACAO_NAO_ATENDE_CRITERIO;
    }

    private Boolean atendeCriterio(Familia familia) {
        return familia.renda().compareTo(RENDA_PADRAO_DO_CRITERIO) <= 0;
    }
}
