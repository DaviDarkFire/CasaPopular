package com.example.casapopular;

import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CasaPopularApplication.class)
@ActiveProfiles("tc")
@ContextConfiguration(initializers = {TesteDeComponente.Initializer.class})
@ExtendWith(SpringExtension.class)
public class TesteDeComponente {

    private List<String> querys = new ArrayList<>();
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer)
            new PostgreSQLContainer("postgres:11.1")
                    .withDatabaseName("integration-tests-db")
                    .withUsername("sa")
                    .withPassword("");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    protected Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        );
    }

    protected void adicionarQuery(String novaQuery) {
        querys.add(novaQuery);
    }

    protected void executarQuerys() throws SQLException {
        Statement statement = this.obterConexao().createStatement();
        querys.forEach(query -> {
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao executar query sql" + e);
            }
        });
    }

}
