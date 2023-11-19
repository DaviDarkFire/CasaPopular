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
    @OneToMany
    private List<Pessoa> pessoas;

    public Familia() {

    }

    public Familia(List<Pessoa> pessoas) {
        this.pessoas = new ArrayList<>(pessoas);
    }

    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public void removerPessoa(Pessoa pessoaASerRemovida) {
        pessoas.removeIf(pessoa -> pessoa.getIdade().equals(pessoaASerRemovida));
    }

    public BigDecimal renda() {
        return pessoas.stream().map(pessoa -> pessoa.getRenda()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long quantidadeDeDependentes() {
        return pessoas.stream().filter(pessoa -> pessoa.getIdade() < 18).count();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
