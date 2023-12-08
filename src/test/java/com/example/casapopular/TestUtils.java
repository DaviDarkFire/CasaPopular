package com.example.casapopular;

import com.example.casapopular.dominio.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TestUtils {
    protected Pessoa criarPessoa(Integer idade, Integer renda) {
        String nomeAleatorio = UUID.randomUUID().toString();
        return new Pessoa(nomeAleatorio, LocalDate.now(), BigDecimal.valueOf(renda));
    }
}
