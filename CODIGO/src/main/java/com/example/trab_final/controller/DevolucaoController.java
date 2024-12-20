package com.example.trab_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.example.trab_final.service.AlunoService;
import com.example.trab_final.service.DevolucaoService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// // logger
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api")
public class DevolucaoController {

    private final DevolucaoService devolucaoService;
    private final AlunoService alunoService;

    // private static final Logger logger = LoggerFactory.getLogger(DevolucaoController.class);

    public DevolucaoController(DevolucaoService devolucaoService, AlunoService alunoService) {
        this.devolucaoService = devolucaoService;
        this.alunoService = alunoService;
    }

    @PostMapping("/devolver")
    public ResponseEntity<Map<String, String>> devolver(
            @RequestParam String matricula,
            @RequestParam String dataDevolucao,
            @RequestParam("codigosLivros") List<String> codigosLivros) {
    
        // logger.info("POST /api/devolver");
        try {
            boolean alunoValido = alunoService.validarAluno(Long.parseLong(matricula));
            
            if (!alunoValido) {
                return ResponseEntity.status(500).body(Map.of("message", "Aluno não encontrado"));
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dataDevolucao, formatter);
                Date data = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                return devolucaoService.realizarDevolucao(Long.parseLong(matricula), data, codigosLivros);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao realizar devolução"));
        }
    }


}
