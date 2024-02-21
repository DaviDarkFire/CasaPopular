package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.aplicacao.comum.Comando;
import com.example.casapopular.aplicacao.PessoaDTO;

import java.util.List;
import java.util.Optional;

public class AdicionaFamiliaComando extends Comando {

    private List<PessoaDTO> pessoas;

    public AdicionaFamiliaComando(List<PessoaDTO> pessoas, Optional<String> usuario) {
        super(usuario);
        this.pessoas = pessoas;
    }

    public List<PessoaDTO> getPessoas() {
        return pessoas;
    }
}
