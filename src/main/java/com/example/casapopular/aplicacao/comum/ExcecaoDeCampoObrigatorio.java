package com.example.casapopular.aplicacao.comum;

import java.util.*;

public class ExcecaoDeCampoObrigatorio extends IllegalArgumentException {

    private List<String> mensagensDeExcecao = new ArrayList<>();

    public ExcecaoDeCampoObrigatorio() {
        super("");
    }
    public ExcecaoDeCampoObrigatorio(String mensagem) {
        super(mensagem);
    }

    public ExcecaoDeCampoObrigatorio quandoNulo(Object objeto, String mensagemDeExcecao) {
        if (Objects.isNull(objeto)) {
            this.mensagensDeExcecao.add(mensagemDeExcecao);
        }
        return this;
    }

    public ExcecaoDeCampoObrigatorio quandoColecaoVazia(Collection colecao, String mensagemDeExcecao) {
        if (!Objects.isNull(colecao) && colecao.isEmpty()) {
            this.mensagensDeExcecao.add(mensagemDeExcecao);
        }
        return this;
    }

    public ExcecaoDeCampoObrigatorio quandoColecaoNula(Collection colecao, String mensagemDeExcecao) {
        if (Objects.isNull(colecao)) {
            this.mensagensDeExcecao.add(mensagemDeExcecao);
        }
        return this;
    }

    public void lancarExcecao() throws Exception {
        if (!this.mensagensDeExcecao.isEmpty()) {
            throw new ExcecaoDeCampoObrigatorio(construirMensagemDeExcecao());
        }
    }

    private String construirMensagemDeExcecao() {
       return this.mensagensDeExcecao.stream().reduce("", (mensagemTotal, excecao) -> mensagemTotal +" "+ excecao);
    }
}
