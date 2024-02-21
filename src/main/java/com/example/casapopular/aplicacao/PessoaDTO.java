package com.example.casapopular.aplicacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PessoaDTO(String nome, LocalDate dataDeNascimento, BigDecimal renda) {}
