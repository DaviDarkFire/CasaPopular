package com.example.casapopular.aplicacao;

import com.example.casapopular.dominio.ProcessoDeSelecao;

public interface RankeiaFamilias {
    ProcessoDeSelecao executar(Integer quantidadeDeFamilias);
}
