package com.example.casapopular.api;

import com.example.casapopular.aplicacao.selecao.ProcessoDeSelecaoDTO;
import com.example.casapopular.aplicacao.selecao.SelecionaFamilias;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/processodeselecao")
public class ProcessoDeSelecaoRest {

    private final SelecionaFamilias selecionaFamilias;

    @Autowired
    public ProcessoDeSelecaoRest(SelecionaFamilias selecionaFamilias) {
        this.selecionaFamilias = selecionaFamilias;
    }

    @PostMapping
    public ResponseEntity<Object> realizarProcessoDeSelecao(@RequestParam Integer quantidadeDeFamilias) {
        //tratar a falta do parâmetro aqui ou no serviço de aplicação?
        try {
            ProcessoDeSelecaoDTO processoDeSelecaoDTO = selecionaFamilias.executar(quantidadeDeFamilias);
            return new ResponseEntity<>(processoDeSelecaoDTO, construirHeaderSucesso(processoDeSelecaoDTO.id()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(construirRespostaBadRequest().toJSONString(), construirHeaderFracasso(), HttpStatus.BAD_REQUEST);
        }
    }

    private HttpHeaders construirHeaderSucesso(Long id) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(location);
        return responseHeader;
    }

    private HttpHeaders construirHeaderFracasso() {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return responseHeader;
    }

    private JSONObject construirRespostaBadRequest() {
        JSONObject json = new JSONObject();
        json.put("title", "Não foi possível criar processo de seleção");
        json.put("detail", "É necessário informar a quantidade de famílias.");
        return json;
    }
}
