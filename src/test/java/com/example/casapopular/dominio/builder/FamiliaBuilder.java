package com.example.casapopular.dominio.builder;

import com.example.casapopular.TesteUtils;
import com.example.casapopular.dominio.Familia;
import com.example.casapopular.dominio.Pessoa;

import java.util.*;

public class FamiliaBuilder {
    private List<Pessoa> pessoas = new ArrayList<>();
    private Long id;

    public Familia criar(){
        Random random = new Random();
        Familia familia = new Familia(pessoas);
        if(Objects.nonNull(this.id)) {
            TesteUtils.alterarCampoPorReflection("id", Familia.class, familia, this.id);
        } else {
            TesteUtils.alterarCampoPorReflection("id", Familia.class, familia, random.nextLong(1000000));
        }
        return familia;
    }

    public FamiliaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public FamiliaBuilder comPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }

    public FamiliaBuilder comPessoas(Pessoa... pessoas) {
        this.pessoas = Arrays.asList(pessoas);
        return this;
    }

    public FamiliaBuilder familiaComRendaAteNovecentosReais() {
        this.pessoas.add(new PessoaBuilder().comRenda(900).criar());
        return this;
    }

    public FamiliaBuilder familiaComRendaAteMilEQuinhentosReais() {
        this.pessoas.add(new PessoaBuilder().comRenda(1500).criar());
        return this;
    }

    public FamiliaBuilder familiaComMaisDeTresDependentes() {
        this.pessoas.addAll(Arrays.asList(new PessoaBuilder().comIdade(10).criar(),
                new PessoaBuilder().comIdade(11).criar(),
                new PessoaBuilder().comIdade(12).criar(),
                new PessoaBuilder().comIdade(13).criar(),
                new PessoaBuilder().comIdade(18).criar()));
        return this;
    }

    public FamiliaBuilder familiaComUmOuDoisDependentes() {
        this.pessoas.addAll(Arrays.asList(new PessoaBuilder().comIdade(10).criar(),
                new PessoaBuilder().comIdade(11).criar(),
                new PessoaBuilder().comIdade(18).criar()));
        return this;
    }
}
