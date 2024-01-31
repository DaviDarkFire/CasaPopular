package com.example.casapopular.aplicacao.adiciona.familia;

import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AdicionaFamiliaConcreto implements AdicionaFamilia {
    private final FamiliaRepositorio familiaRepositorio;
    public AdicionaFamiliaConcreto(FamiliaRepositorio familiaRepositorio) {
        this.familiaRepositorio = familiaRepositorio;
    }

    @Override
    public void executar(AdicionaFamiliaComando comando) {
    }
}
