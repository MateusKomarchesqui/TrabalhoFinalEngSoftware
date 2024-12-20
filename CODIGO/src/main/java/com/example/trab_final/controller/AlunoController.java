package com.example.trab_final.controller;

import com.example.trab_final.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//logger
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class AlunoController {

    private final AlunoService alunoService;

    // private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Cadastro de aluno
    @PostMapping("/cadastrarAluno")
    public ResponseEntity<Map<String, String>> cadastrarAluno(@RequestParam Long matricula, 
            @RequestParam String nome, 
            @RequestParam long cpf, 
            @RequestParam String endereco) {

        Map<String, String> response = new HashMap<>();

        boolean alunoValido = alunoService.validarAluno(matricula);

        if (alunoValido) {
            response.put("message", "Matricula já cadastrada!");
            return ResponseEntity.status(400).body(response);
        }

        boolean cadastrado = alunoService.cadastrarAluno(matricula, nome, cpf, endereco);

        if (!cadastrado) {
            response.put("message", "Erro ao cadastrar aluno.");
            return ResponseEntity.status(400).body(response);
        }
        
        response.put("message", "Aluno cadastrado com sucesso!");
        return ResponseEntity.ok(response);
    }
}
