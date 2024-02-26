package com.example.casapopular.dominio;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Pessoa")
public class Pessoa {

    @Transient
    private final Integer IDADE_MINIMA_PARA_SER_DEPENDENTE = 18;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "familia")
    private Familia familia;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;
    @Column(name = "renda")
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

    public boolean ehDependente() {
        return this.getIdade() < IDADE_MINIMA_PARA_SER_DEPENDENTE;
    }
}
