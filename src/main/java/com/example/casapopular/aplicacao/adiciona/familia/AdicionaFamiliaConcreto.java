package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.aplicacao.selecao.PessoaDTO;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdicionaFamiliaConcreto implements AdicionaFamilia {
    private static final String MENSAGEM_ESPERADA_PARA_COMANDO_INVALIDO = "Impossível adicionar família. Dados Inválidos.";
    private final FamiliaRepositorio familiaRepositorio;
    public AdicionaFamiliaConcreto(FamiliaRepositorio familiaRepositorio) {
        this.familiaRepositorio = familiaRepositorio;
    }

    @Override
    public void executar(AdicionaFamiliaComando comando) throws Exception {
        validarInformacoes(comando);
        List<Pessoa> pessoas = mapearPessoas(comando.getPessoas());
        Familia familia = new Familia(pessoas);
        familiaRepositorio.save(familia);
    }

    private void validarInformacoes(AdicionaFamiliaComando comando) throws Exception {
        if (Objects.isNull(comando)) {
            throw new Exception(MENSAGEM_ESPERADA_PARA_COMANDO_INVALIDO);
        }

    }

    private List<Pessoa> mapearPessoas(List<PessoaDTO> pessoasDTO) {
        return pessoasDTO
                .stream()
                .map(pessoaDTO -> new Pessoa(pessoaDTO.nome(), pessoaDTO.dataDeNascimento(), pessoaDTO.renda()))
                .collect(Collectors.toList());
    }
}
