package com.example.casapopular.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private BigDecimal renda;

    public Pessoa() {

    }

    public Pessoa(String nome, Integer idade, BigDecimal renda){
        this.nome = nome;
        this.idade = idade;
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

    public Integer getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }
}
