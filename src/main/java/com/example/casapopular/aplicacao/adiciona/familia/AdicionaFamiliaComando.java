package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.aplicacao.comum.Comando;
import com.example.casapopular.aplicacao.PessoaDTO;

import java.util.List;
import java.util.Optional;

public class AdicionaFamiliaComando extends Comando {

    private String nomeDaFamilia;
    private List<PessoaDTO> pessoas;

    public AdicionaFamiliaComando(String nomeDaFamilia, List<PessoaDTO> pessoas, Optional<String> usuario) {
        super(usuario);
        this.nomeDaFamilia = nomeDaFamilia;
        this.pessoas = pessoas;
    }

    public List<PessoaDTO> getPessoas() {
        return pessoas;
    }
}
