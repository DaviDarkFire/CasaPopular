package com.example.casapopular.api;

import com.example.casapopular.TesteDeComponente;
import com.example.casapopular.dominio.repositorio.FamiliaRepositorio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoDeSelecaoRestTest extends TesteDeComponente {

    @Autowired
    private ProcessoDeSelecaoRest processoDeSelecaoRest;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws SQLException {
        this.limparBanco();
    }


    @Test
    void deveCriarProcessoDeSelecao() throws Exception {
        popularBancoParaTeste();
        String primeiraParteEsperadaDaResposta = "\"id\":1,\"dataDeSelecao\"";
        String segundaParteEsperadaDaResposta = "\"familias\":[{\"id\":2,\"pontuacao\":8},{\"id\":1,\"pontuacao\":7},{\"id\":3,\"pontuacao\":7}]";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/processodeselecao")
                        .param("quantidadeDeFamilias", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", equalTo("http://localhost:8080/processodeselecao/1")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(primeiraParteEsperadaDaResposta)))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(segundaParteEsperadaDaResposta)));
    }

    @Test
    void deveRetornarStatusDeErroCasoHajaAlgumProblemaAoCriarProcessoDeSelecao() {

    }

    private void popularBancoParaTeste() throws SQLException {
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Santos');");
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Santos');");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (1, 'IVAN', '1983-11-02', 295.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (1, 'RAQUEL', '2007-10-22', 422.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (1, 'TATIANA', '2008-4-19', 982.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (1, 'CAIO', '1987-8-28', 127.0);");
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Geromel');");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (2, 'VALTER', '1983-11-02', 289.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (2, 'FABIANA', '2015-2-15', 364.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (2, 'DIEGO', '2015-6-21', 84.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (2, 'ELIANE', '2013-2-28', 609.0);");
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Machado');");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (3, 'WAGNER', '1983-11-02', 688.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (3, 'JULIA', '2015-11-3', 1482.0);");
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Geromel');");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (4, 'TAMIRES', '1983-11-02', 98.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (4, 'LAURA', '1998-6-22', 1189.0);");
        adicionarQuery("INSERT INTO Familia(nome) VALUES ('Marioto');");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (5, 'ADAO', '1983-11-02', 496.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (5, 'HELIO', '1992-7-7', 735.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (5, 'IGOR', '1981-2-11', 191.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (5, 'IVAN', '2003-3-26', 928.0);");
        adicionarQuery("INSERT INTO Pessoa(familia, nome, data_de_nascimento, renda) VALUES (5, 'VANIA', '1996-6-23', 812.0);");
        executarQuerys();
    }

    @AfterAll
    static void after() {
        postgreSQLContainer.stop();
    }
}