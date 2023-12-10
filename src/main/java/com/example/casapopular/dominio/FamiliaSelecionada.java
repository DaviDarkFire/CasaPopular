package com.example.casapopular.dominio;

public class FamiliaSelecionada extends ObjetoDeValor {

    private Long idDaFamiliaSelecionada;
    private Integer pontuacao;

    public FamiliaSelecionada(Long idDaFamiliaSelecionada, Integer pontuacao) {
        this.idDaFamiliaSelecionada = idDaFamiliaSelecionada;
        this.pontuacao = pontuacao;
    }

    public Long getIdDaFamiliaSelecionada() {
        return idDaFamiliaSelecionada;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }


    @Override
    protected Object[] attributes() {
        return new Object[]{this.idDaFamiliaSelecionada, this.pontuacao};
    }
}
