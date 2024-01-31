package com.example.casapopular.aplicacao.comum;

public interface ServicoDeAplicacaoDeComando <C extends Comando>{
    void executar(C comando);
}
