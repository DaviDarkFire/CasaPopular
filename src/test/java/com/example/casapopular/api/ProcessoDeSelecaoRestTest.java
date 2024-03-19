package com.example.casapopular.api;

import com.example.casapopular.TesteDeComponente;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProcessoDeSelecaoRestTest extends TesteDeComponente {

    @Autowired
    private FamiliaRepositorio familiaRepositorio;

    @BeforeEach
    void setUp() throws SQLException {
       adicionarQuery("INSERT INTO Familia(nome) VALUES ('Santos');");
       executarQuerys();
    }


    @Test
    void deveCriarProcessoDeSelecao() throws IOException {
        familiaRepositorio.findAll();
        assertNotNull(familiaRepositorio);

    }

    @Test
    void deveRetornarStatusDeErroCasoHajaAlgumProblemaAoCriarProcessoDeSelecao() {

    }

    @AfterAll
    static void tearDown() {
        postgreSQLContainer.stop();
    }
}