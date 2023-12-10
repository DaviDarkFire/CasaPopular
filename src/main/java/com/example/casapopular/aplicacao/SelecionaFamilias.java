package com.example.casapopular.aplicacao;

import com.example.casapopular.dominio.ProcessoDeSelecao;

public interface SelecionaFamilias {
    ProcessoDeSelecaoDTO executar(Integer quantidadeDeFamilias);
}
