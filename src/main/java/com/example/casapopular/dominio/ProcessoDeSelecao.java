package com.example.casapopular.dominio;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ProcessoDeSelecao")
public class ProcessoDeSelecao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<FamiliaSelecionada> familiasSelecionadas;

    private LocalDateTime dataDeSelecao;

    private ProcessoDeSelecao() {
    }

    public ProcessoDeSelecao(List<FamiliaSelecionada> familiasSelecionadas) {
        this.familiasSelecionadas = familiasSelecionadas;
        this.dataDeSelecao = LocalDateTime.now();
    }

    public List<FamiliaSelecionada> getFamiliasSelecionadas() {
        return familiasSelecionadas;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataDeSelecao() {
        return dataDeSelecao;
    }
}
