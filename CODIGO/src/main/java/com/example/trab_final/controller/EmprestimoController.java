package com.example.trab_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.trab_final.service.AlunoService;
import com.example.trab_final.service.EmprestimoService;
import com.example.trab_final.service.DebitoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final AlunoService alunoService;
    private final DebitoService debitoService;

    public EmprestimoController(EmprestimoService emprestimoService, AlunoService alunoService, DebitoService debitoService) {
        this.emprestimoService = emprestimoService;
        this.alunoService = alunoService;
        this.debitoService = debitoService;
    }

    @PostMapping("/emprestar")
    // map<string, string>: retorno json com chave e valor
    public ResponseEntity<Map<String, String>> emprestar(
            @RequestParam String matricula,
            @RequestParam("codigosLivros") List<String> codigosLivros) {
    
        try {
            boolean alunoValido = alunoService.validarAluno(Long.parseLong(matricula));
            
            if (!alunoValido) {
                return ResponseEntity.status(500).body(Map.of("message", "Aluno não encontrado"));
            } else {
                boolean debitoAtivo = debitoService.verificarDebito(Long.parseLong(matricula));

                if (debitoAtivo) {
                    return ResponseEntity.status(500).body(Map.of("message", "Aluno com débito ativo"));
                } else {
                    return emprestimoService.realizarEmprestimo(Long.parseLong(matricula), codigosLivros);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao realizar empréstimo"));
        }
    }
}
