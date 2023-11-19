package com.example.casapopular.dominio;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ProcessoDeSelecao")
public class ProcessoDeSelecao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Familia> familias;

    public ProcessoDeSelecao() {
    }

    public ProcessoDeSelecao(List<Familia> familias) {
        this.familias = familias;
    }
}
