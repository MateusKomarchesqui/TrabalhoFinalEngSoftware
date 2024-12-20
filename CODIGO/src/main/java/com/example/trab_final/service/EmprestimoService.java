package com.example.trab_final.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.trab_final.model.Emprestimo;
import com.example.trab_final.model.ItemEmprestimo;
import com.example.trab_final.repository.EmprestimoDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import java.util.*;

@Service
public class EmprestimoService {

    private final ItemEmprestimoService itemEmprestimoService;

    public EmprestimoService(ItemEmprestimoService itemEmprestimoService) {
        this.itemEmprestimoService = itemEmprestimoService;
    }

    public ResponseEntity<Map<String, String>> realizarEmprestimo(Long matricula, List<String> codigosLivros) {
        Map<String, String> response = new HashMap<>();

        try {
            List<ItemEmprestimo> itens = itemEmprestimoService.processarItensEmprestimo(codigosLivros);

            if (itens.isEmpty()) {
                response.put("message", "Nenhum livro disponível para empréstimo.");
                return ResponseEntity.status(400).body(response);
            }

            Date dataPrevista = calculaDataDevolucao(itens);

            salvarEmprestimo(matricula, itens, dataPrevista);

            if (itens.size() < codigosLivros.size()) {
                response.put("message", "Alguns livros não estão disponíveis para empréstimo.");
                return ResponseEntity.ok(response);
            }

            response.put("message", "Empréstimo realizado com sucesso!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Erro ao realizar empréstimo.");
            return ResponseEntity.status(500).body(response);
        }
    }

    private Emprestimo salvarEmprestimo(Long matricula, List<ItemEmprestimo> itens, Date dataPrevista) {
        Emprestimo emprestimo = new Emprestimo(new Date(), dataPrevista, matricula, itens);

        EmprestimoDAO emprestimoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getEmprestimoDAO();
        emprestimo = emprestimoDAO.create(emprestimo);

        itemEmprestimoService.createItensEmprestimo(itens, emprestimo.getId(), dataPrevista);

        return emprestimo;
    }

    public Date calculaDataDevolucao(List<ItemEmprestimo> itens) {
        Date dataPrevista = itens.stream()
                .map(ItemEmprestimo::getDataPrevista)
                .max(Date::compareTo)
                .orElse(new Date());

        if (itens.size() > 2) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataPrevista);
            calendar.add(Calendar.DATE, (itens.size() - 2) * 2);
            dataPrevista = calendar.getTime();
        }

        return dataPrevista;
    }

    public List<Emprestimo> readEmprestimosByMatricula(long matricula) {
        EmprestimoDAO emprestimoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getEmprestimoDAO();
        List<Emprestimo> emprestimos = emprestimoDAO.readByMatricula(matricula);

        emprestimos.forEach(emprestimo -> 
            emprestimo.setItens(
                itemEmprestimoService.readItensEmprestimoByEmprestimoId(emprestimo.getId())
            )
        );

        return emprestimos;
    }

    public List<Emprestimo> getEmprestimosComPendencia(List<Emprestimo> emprestimos) {
        List<Emprestimo> emprestimosComPendencia = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getItens().stream().anyMatch(item -> item.getDataDevolucao() == null)) {
                emprestimosComPendencia.add(emprestimo);
            }
        }

        return emprestimosComPendencia;
    }

    public List<Emprestimo> buscarEmprestimosPendentes(long matricula) {
        List<Emprestimo> emprestimos = readEmprestimosByMatricula(matricula);
        return getEmprestimosComPendencia(emprestimos);
    }
}
