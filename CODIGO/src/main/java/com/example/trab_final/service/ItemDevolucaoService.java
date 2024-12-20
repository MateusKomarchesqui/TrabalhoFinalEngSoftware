package com.example.trab_final.service;

import org.springframework.stereotype.Service;

import com.example.trab_final.model.Emprestimo;
import com.example.trab_final.model.ItemDevolucao;
import com.example.trab_final.model.ItemEmprestimo;
import com.example.trab_final.model.Livro;
import com.example.trab_final.repository.ItemDevolucaoDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ItemDevolucaoService {

    private final LivroService livroService;
    private final ItemEmprestimoService itemEmprestimoService;

    public ItemDevolucaoService(LivroService livroService, ItemEmprestimoService itemEmprestimoService) {
        this.livroService = livroService;
        this.itemEmprestimoService = itemEmprestimoService;
    }

    public void createItensDevolucao(List<ItemDevolucao> itensDevolucao, Long devolucaoId) {
        ItemDevolucaoDAO itemDevolucaoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getItemDevolucaoDAO();
        for (ItemDevolucao item : itensDevolucao) {
            item.setDevolucao_id(devolucaoId);
            itemDevolucaoDAO.create(item);
        }
    }

    public List<ItemDevolucao> readItensDevolucaoByDevolucaoId(Long devolucaoId) {
        ItemDevolucaoDAO itemDevolucaoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getItemDevolucaoDAO();
        List<ItemDevolucao> itens = itemDevolucaoDAO.readByDevolucaoId(devolucaoId);

        for (ItemDevolucao item : itens) {
            Livro livro = livroService.getLivroByItemDevolucaoId(item.getId());
            item.setLivro(livro);
        }
        return itens;
    }

    public List<ItemDevolucao> processarItensDevolucao(Emprestimo emprestimo, List<String> codigosLivros, Date dataDevolucao) {
        List<ItemDevolucao> itensDevolucao = new ArrayList<>();

        for (String codigoLivro : codigosLivros) {
            ItemEmprestimo item = itemEmprestimoService.getItemEmprestimoByIsbn(codigoLivro, emprestimo.getItens());

            if (item != null && item.getDataDevolucao() == null) {
                Livro livro = itemEmprestimoService.getLivroByItemEmprestimo(item);
                livroService.devolverLivro(livro);

                int diasAtraso = calcularDiasAtraso(itemEmprestimoService.getDataPrevista(item), dataDevolucao);
                itensDevolucao.add(criarItemDevolucao(livro, dataDevolucao, diasAtraso));

                itemEmprestimoService.updateDataDevolucaoItemEmprestimo(item, dataDevolucao);
            }
        }

        return itensDevolucao;
    }

    public int calcularDiasAtraso(Date dataPrevista, Date dataDevolucao) {
        long diff = dataDevolucao.getTime() - dataPrevista.getTime();
        int diasAtraso = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return diasAtraso > 0 ? diasAtraso : 0;
    }

    private ItemDevolucao criarItemDevolucao(Livro livro, Date dataDevolucao, int diasAtraso) {
        ItemDevolucao itemDevolucao = new ItemDevolucao();
        itemDevolucao.setLivro(livro);
        itemDevolucao.setDataDevolucao(dataDevolucao);
        itemDevolucao.setDiasAtraso(diasAtraso);
        return itemDevolucao;
    }

    public int calcularDiasAtrasoTotal(List<ItemDevolucao> itensDevolucao) {
        return itensDevolucao.stream().mapToInt(ItemDevolucao::getDiasAtraso).sum();
    }

}
