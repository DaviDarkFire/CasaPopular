package com.example.casapopular.dominio;

import java.util.Arrays;

public abstract class ObjetoDeValor {
    protected abstract Object[] attributes();
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (objeto == null || getClass() != objeto.getClass()) return false;

        ObjetoDeValor objetoASerComparado = (ObjetoDeValor) objeto;
        return Arrays.deepEquals(this.attributes(), objetoASerComparado.attributes());
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(attributes());
    }
}
