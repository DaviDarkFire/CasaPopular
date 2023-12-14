package com.example.casapopular.dominio;

import jakarta.persistence.Embeddable;

@Embeddable
public class FamiliaSelecionada extends ObjetoDeValor {

    private Long idDaFamiliaSelecionada;
    private Integer pontuacao;

    public FamiliaSelecionada(Long idDaFamiliaSelecionada, Integer pontuacao) {
        this.idDaFamiliaSelecionada = idDaFamiliaSelecionada;
        this.pontuacao = pontuacao;
    }

    public FamiliaSelecionada() {

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

    /**
     * Fui obrigado a criar os setters por causa da anotação do Spring @Embedabble, se eu não colocasse o compilador
     * ficava reclamando. Pra contornar a situação coloquei os setters como privados para que ngm tenha acesso e assim
     * não quebre o conceito de value object do DDD
     */
    private void setIdDaFamiliaSelecionada(Long idDaFamiliaSelecionada) {
        this.idDaFamiliaSelecionada = idDaFamiliaSelecionada;
    }
    private void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}
