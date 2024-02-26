package com.example.casapopular.api;

import com.example.casapopular.aplicacao.selecao.ProcessoDeSelecaoDTO;
import com.example.casapopular.aplicacao.selecao.SelecionaFamilias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processodeselecao")
public class ProcessoDeSelecaoRest {

    private final SelecionaFamilias selecionaFamilias;

    @Autowired
    public ProcessoDeSelecaoRest(SelecionaFamilias selecionaFamilias) {
        this.selecionaFamilias = selecionaFamilias;
    }

    @PostMapping
    public ResponseEntity<ProcessoDeSelecaoDTO> realizarProcessoDeSelecao(@RequestParam Integer quantidadeDeFamilias) {
        try {
            ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);
            return new ResponseEntity<>(processoDeSelecaoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProcessoDeSelecaoDTO(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
