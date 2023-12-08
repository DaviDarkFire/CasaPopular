package com.example.casapopular.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private BigDecimal renda;

    public Pessoa() {

    }

    public Pessoa(String nome, LocalDate dataDeNascimento, BigDecimal renda){
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.renda = renda;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return Period.between(dataDeNascimento, LocalDate.now()).getYears();
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }
}
