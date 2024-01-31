package com.example.casapopular.aplicacao;

import java.time.LocalDateTime;
import java.util.List;

public record ProcessoDeSelecaoDTO(Long id, LocalDateTime dataDeSelecao, List<FamiliaSelecionadaDTO> familias) {}
