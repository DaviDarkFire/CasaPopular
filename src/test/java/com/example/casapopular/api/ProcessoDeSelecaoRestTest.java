package com.example.casapopular.api;

import com.example.casapopular.CasaPopularApplication;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CasaPopularApplication.class)
@ActiveProfiles("tc")
@ContextConfiguration(initializers = {ProcessoDeSelecaoRestTest.Initializer.class})
@ExtendWith(SpringExtension.class)
public class ProcessoDeSelecaoRestTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer)
            new PostgreSQLContainer("postgres:11.1")
                    .withDatabaseName("integration-tests-db")
                    .withUsername("sa")
                    .withPassword("sa")
                    .withInitScript("data_tests.sql");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start(); // Start the container before obtaining the JDBC URL
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private FamiliaRepositorio familiaRepositorio;

    @Test
    void deveCriarProcessoDeSelecao() throws IOException {
        assertNotNull(familiaRepositorio);
        // Add your test logic here
    }

    @Test
    void deveRetornarStatusDeErroCasoHajaAlgumProblemaAoCriarProcessoDeSelecao() {
        // Add your test logic here
    }

    @AfterAll
    static void tearDown() {
        postgreSQLContainer.stop();
    }
}