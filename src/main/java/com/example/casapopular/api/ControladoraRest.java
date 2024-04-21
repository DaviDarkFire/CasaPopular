package com.example.casapopular.api;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@ControllerAdvice
@RestControllerAdvice
public class ControladoraRest extends ResponseEntityExceptionHandler {

    protected String tituloDoRetorno = "Não foi possível criar recurso.";
    protected String detalhesDoRetorno = "Foi encontrado um erro inesperado.";
    protected MediaType mediaType = MediaType.APPLICATION_PROBLEM_JSON_UTF8;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {
        return super.handleExceptionInternal(ex, construirRespostaBadRequest(), construirHeaderFracasso(), statusCode, request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleExcecaoDosServicos(RuntimeException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, construirRespostaBadRequest(), construirHeaderFracasso(), HttpStatus.BAD_REQUEST, request);
    }

    protected HttpHeaders construirHeaderSucesso(Long identificadorDoRecursoCriado) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(identificadorDoRecursoCriado)
                .toUri();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(location);
        return responseHeader;
    }

    protected HttpHeaders construirHeaderFracasso() {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(mediaType);
        return responseHeader;
    }

    protected JSONObject construirRespostaBadRequest() {
        JSONObject json = new JSONObject();
        json.put("title", tituloDoRetorno);
        json.put("detail", detalhesDoRetorno);
        return json;
    }
}
