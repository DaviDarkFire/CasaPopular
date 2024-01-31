package com.example.casapopular.aplicacao.comum;

import java.util.Optional;

public abstract class Comando {

    private Optional<String> usuario;

    protected Comando(Optional<String> usuario) {
        this.usuario = usuario;
    }

    public Optional<String> getUsuario() {
        return usuario;
    }
}
