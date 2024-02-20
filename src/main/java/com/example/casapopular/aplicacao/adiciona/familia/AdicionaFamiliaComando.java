package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.aplicacao.comum.Comando;
import com.example.casapopular.aplicacao.selecao.PessoaDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdicionaFamiliaComando extends Comando {

    private List<PessoaDTO> pessoas;

    protected AdicionaFamiliaComando(List<PessoaDTO> pessoas, Optional<String> usuario) {
        super(usuario);
        this.pessoas = pessoas;
    }

    public List<PessoaDTO> getPessoas() {
        return pessoas;
    }
}
