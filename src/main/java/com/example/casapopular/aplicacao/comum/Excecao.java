package com.example.casapopular.aplicacao.comum;

import java.util.*;

public class Excecao {

    private List<String> mensagensDeExcecao = new ArrayList<>();

    public Excecao quandoNulo(Object objeto, String mensagemDeExcecao) {
        if (Objects.isNull(objeto)) {
            this.mensagensDeExcecao.add(mensagemDeExcecao);
        }
        return this;
    }

    public Excecao quandoColecaoVazia(Collection colecao, String mensagemDeExcecao) {
        if (colecao.isEmpty()) {
            this.mensagensDeExcecao.add(mensagemDeExcecao);
        }
        return this;
    }

    public Excecao quandoColecaoNula(Collection colecao, String mensagemDeExcecao) {
        if (Objects.isNull(colecao)) {
            this.mensagensDeExcecao.add(mensagemDeExcecao);
        }
        return this;
    }

    public void lancarExcecao() throws Exception {
        if (!this.mensagensDeExcecao.isEmpty()) {
            throw new Exception(construirMensagemDeExcecao());
        }
    }

    private String construirMensagemDeExcecao() {
       return this.mensagensDeExcecao.stream().reduce("", (mensagemTotal, excecao) -> mensagemTotal +"\n"+ excecao);
    }
}
