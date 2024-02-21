package com.example.casapopular.api;

import com.example.casapopular.aplicacao.FamiliaDTO;
import com.example.casapopular.aplicacao.adiciona.familia.AdicionaFamilia;
import com.example.casapopular.aplicacao.adiciona.familia.AdicionaFamiliaComando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FamiliaRest {

    private final AdicionaFamilia adicionaFamilia;

    @Autowired
    public FamiliaRest(AdicionaFamilia adicionaFamilia) {
        this.adicionaFamilia = adicionaFamilia;
    }

    @PostMapping("/familia")
    public ResponseEntity<String> adicionarFamilia(@RequestBody FamiliaDTO familiaDTO) {
        AdicionaFamiliaComando comando = new AdicionaFamiliaComando(familiaDTO.pessoas(), Optional.empty());
        try {
            adicionaFamilia.executar(comando);
        } catch (Exception e) {
            System.out.println("Deu xabu hein mussarelo: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
