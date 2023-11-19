package com.example.casapopular.dominio.criterio;

import com.example.casapopular.dominio.Familia;

public interface Criterio {
    Integer pontuacao(Familia familia);
    Boolean atendeCriterio(Familia familia);
}
