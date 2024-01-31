package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.aplicacao.comum.Comando;
import com.example.casapopular.aplicacao.selecao.PessoaDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdicionaFamiliaComando extends Comando {

    private final static String MENSAGEM_ESPERADA_PARA_LISTA_DE_PESSOAS_INVALIDA = "Lista de pessoas inválida.";
    private List<PessoaDTO> pessoas;

    protected AdicionaFamiliaComando(List<PessoaDTO> pessoas, Optional<String> usuario) throws Exception {
        super(usuario);
        validarDados(pessoas);
        this.pessoas = pessoas;
    }

    private void validarDados(List<PessoaDTO> pessoas) throws Exception{
        if(Objects.isNull(pessoas) || pessoas.isEmpty()) {
            throw new Exception(MENSAGEM_ESPERADA_PARA_LISTA_DE_PESSOAS_INVALIDA);
        }
    }

    public List<PessoaDTO> getPessoas() {
        return pessoas;
    }
}
