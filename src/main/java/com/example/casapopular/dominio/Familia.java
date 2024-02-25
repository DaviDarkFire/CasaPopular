package com.example.casapopular.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Familia")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pessoa> pessoas;

    public Familia() {

    }

    public Familia(List<Pessoa> pessoas) {
        this.pessoas = new ArrayList<>(pessoas);
    }

    public BigDecimal renda() {
        return pessoas.stream()
                .filter(pessoa -> !pessoa.ehDependente())
                .map(Pessoa::getRenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long quantidadeDeDependentes() {
        return pessoas.stream().filter(Pessoa::ehDependente).count();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public Long getId() {
        return id;
    }
}
