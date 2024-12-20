package com.example.trab_final.service;

import org.springframework.stereotype.Service;

import com.example.trab_final.model.Emprestimo;
import com.example.trab_final.model.ItemDevolucao;
import com.example.trab_final.repository.DevolucaoDAO;
import com.example.trab_final.repository.factory.DAOFactory;
import com.example.trab_final.model.Devolucao;

import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DevolucaoService {

    private final EmprestimoService emprestimoService;
    private final ItemDevolucaoService itemDevolucaoService;
    private final MultaService multaService;

    private static final Logger logger = LoggerFactory.getLogger(DevolucaoService.class);

    public DevolucaoService(EmprestimoService emprestimoService, ItemEmprestimoService itemEmprestimoService, ItemDevolucaoService itemDevolucaoService, LivroService livroService, MultaService multaService) {
        this.emprestimoService = emprestimoService;
        this.itemDevolucaoService = itemDevolucaoService;
        this.multaService = multaService;
    }

    public ResponseEntity<Map<String, String>> realizarDevolucao(long matricula, Date dataDevolucao, List<String> codigosLivros) {
        Map<String, String> response = new HashMap<>();
        int diasAtrasoTotal = 0;

        List<Emprestimo> emprestimosPendentes = emprestimoService.buscarEmprestimosPendentes(matricula);

        for (Emprestimo emprestimo : emprestimosPendentes) {
            List<ItemDevolucao> itensDevolucao = itemDevolucaoService.processarItensDevolucao(emprestimo, codigosLivros, dataDevolucao);

            if (!itensDevolucao.isEmpty()) {
                diasAtrasoTotal += itemDevolucaoService.calcularDiasAtrasoTotal(itensDevolucao);

                float multa = aplicarMultaSeNecessario(matricula, dataDevolucao, diasAtrasoTotal);

                Devolucao devolucao = processarDevolucao(emprestimo, itensDevolucao, multa);

                itemDevolucaoService.createItensDevolucao(itensDevolucao, devolucao.getId());
            } else {
                response.put("message", "Nenhum livro encontrado para devolução");
                return ResponseEntity.status(500).body(response);
            }
        }

        response.put("message", "Devolução realizada com sucesso!");
        return ResponseEntity.ok(response);
    }

    private float aplicarMultaSeNecessario(long matricula, Date dataDevolucao, int diasAtrasoTotal) {
        if (diasAtrasoTotal > 0) {
            float multa = multaService.calcularMulta(matricula, dataDevolucao, diasAtrasoTotal);
            logger.info("Multa gerada: " + multa);
            return multa;
        }
        return 0;
    }

    private Devolucao processarDevolucao(Emprestimo emprestimo, List<ItemDevolucao> itensDevolucao, float multa) {
        Devolucao devolucao = getDevolucaoByEmprestimoId(emprestimo.getId());

        if (devolucao == null) {
            devolucao = criarNovaDevolucao(emprestimo, itensDevolucao);
        }

        return atualizarMultaDevolucao(devolucao, multa);
    }

    private Devolucao criarNovaDevolucao(Emprestimo emprestimo, List<ItemDevolucao> itensDevolucao) {
        Devolucao devolucao = new Devolucao(emprestimo.getId(), itensDevolucao);
        DevolucaoDAO devolucaoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getDevolucaoDAO();
        return devolucaoDAO.create(devolucao);
    }

    public Devolucao atualizarMultaDevolucao(Devolucao devolucao, float multa) {
        float pendencia = devolucao.getValorMulta();
        devolucao.setValorMulta(pendencia + multa);

        DevolucaoDAO devolucaoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getDevolucaoDAO();
        return devolucaoDAO.update(devolucao);
    }

    public Devolucao getDevolucaoByEmprestimoId(long emprestimoId) {
        DevolucaoDAO devolucaoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getDevolucaoDAO();
        Devolucao devolucao = devolucaoDAO.readByEmprestimoId(emprestimoId);

        if (devolucao != null) {
            List<ItemDevolucao> itens = itemDevolucaoService.readItensDevolucaoByDevolucaoId(devolucao.getId());
            devolucao.setItens(itens);
        }

        return devolucao;
    }
}
