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
        this.limparBanco();
    }


    @Test
    void deveCriarProcessoDeSelecao() throws IOException, SQLException {
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Santos');");
        executarQuerys();
        familiaRepositorio.findAll();
        assertNotNull(familiaRepositorio);

    }

    @Test
    void deveRetornarStatusDeErroCasoHajaAlgumProblemaAoCriarProcessoDeSelecao() throws SQLException {
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Silva');");
        executarQuerys();
        familiaRepositorio.findAll();
    }

    @AfterAll
    static void after() {
        postgreSQLContainer.stop();
    }
}