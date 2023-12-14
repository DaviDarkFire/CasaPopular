package com.example.casapopular.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Familia")
public class Familia {

    private final Integer IDADE_MINIMA_PARA_CONSIDERAR_DEPENDENTE = 18;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Pessoa> pessoas;

    public Familia() {

    }

    public Familia(List<Pessoa> pessoas) {
        this.pessoas = new ArrayList<>(pessoas);
    }

    public BigDecimal renda() {
        return pessoas.stream()
                .filter(pessoa -> pessoa.getIdade() >= IDADE_MINIMA_PARA_CONSIDERAR_DEPENDENTE)
                .map(Pessoa::getRenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long quantidadeDeDependentes() {
        return pessoas.stream().filter(pessoa -> pessoa.getIdade() < IDADE_MINIMA_PARA_CONSIDERAR_DEPENDENTE).count();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Long getId() {
        return id;
    }
}
