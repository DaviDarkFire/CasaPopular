package com.example.casapopular.api;

import com.example.casapopular.aplicacao.selecao.ProcessoDeSelecaoDTO;
import com.example.casapopular.aplicacao.selecao.SelecionaFamilias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/processodeselecao")
@ControllerAdvice
@RestControllerAdvice
public class ProcessoDeSelecaoRest extends ControladoraRest {

    protected static final String TITULO_RETORNO_BAD_REQUEST = "Não foi possível criar processo de seleção.";
    protected static final String DETALHES_RETORNO_BAD_REQUEST = "É necessário informar a quantidade de famílias.";
    private final SelecionaFamilias selecionaFamilias;

    @Autowired
    public ProcessoDeSelecaoRest(SelecionaFamilias selecionaFamilias) {
        this.selecionaFamilias = selecionaFamilias;
        definirMensagemParaRequisicaoBadRequest();
    }

    @PostMapping
    public ResponseEntity<Object> realizarProcessoDeSelecao(@RequestParam Integer quantidadeDeFamilias) {
        //tratar a falta do parâmetro aqui ou no serviço de aplicação?
        try {
            ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);
            return new ResponseEntity<>(processoDeSelecaoDTO, construirHeaderSucesso(processoDeSelecaoDTO.id()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(construirRespostaBadRequest(), construirHeaderFracasso(), HttpStatus.BAD_REQUEST);
        }
    }

    private void definirMensagemParaRequisicaoBadRequest() {
        tituloDoRetorno = TITULO_RETORNO_BAD_REQUEST;
        detalhesDoRetorno = DETALHES_RETORNO_BAD_REQUEST;
        mediaType = MediaType.APPLICATION_PROBLEM_JSON_UTF8;
    }
}
