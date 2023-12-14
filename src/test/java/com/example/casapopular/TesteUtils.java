package com.example.casapopular;

import java.lang.reflect.Field;
import java.util.Optional;

public class TesteUtils {

    public static void alterarCampoPorReflection(String campo, Class classe, Object objetoQuePossuiOCampo, Object novoValorDoCampo) {
        try {
            Optional<Field> fieldEncontrado = obterCampoNaClasse(campo, classe);
            if (fieldEncontrado.isPresent()) {
                Field field = fieldEncontrado.get();
                field.setAccessible(true);
                field.set(objetoQuePossuiOCampo, novoValorDoCampo);
                field.setAccessible(false);
            }

        } catch (Exception excecao) {
            throw new IllegalArgumentException();
        }
    }

    private static Optional<Field> obterCampoNaClasse(String nomeDoCampo, Class classe) {
        try {
            return Optional.ofNullable(classe.getDeclaredField(nomeDoCampo));
        } catch (NoSuchFieldException var3) {
            return classe.getSuperclass() != Object.class ? obterCampoNaClasse(nomeDoCampo, classe.getSuperclass()) : Optional.empty();
        }
    }
}
