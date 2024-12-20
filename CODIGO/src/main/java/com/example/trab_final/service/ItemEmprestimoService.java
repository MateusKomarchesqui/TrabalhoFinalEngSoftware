package com.example.trab_final.service;

import org.springframework.stereotype.Service;

import com.example.trab_final.model.Livro;
import com.example.trab_final.repository.ItemEmprestimoDAO;
import com.example.trab_final.repository.factory.DAOFactory;
import com.example.trab_final.model.ItemEmprestimo;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class ItemEmprestimoService {

    private final LivroService livroService;

    public ItemEmprestimoService(LivroService livroService) {
        this.livroService = livroService;
    }

    // facilita manutenção posterior
    public ItemEmprestimo instanciarItemEmprestimo(Livro livro) {
        ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
        itemEmprestimo.setLivro(livro);
        itemEmprestimo.setDataPrevista(calcularDataPrevista(itemEmprestimo));
        return itemEmprestimo;
    }

    private Date calcularDataPrevista(ItemEmprestimo itemEmprestimo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int prazo = livroService.getPrazo(itemEmprestimo.getLivro());
        calendar.add(Calendar.DATE, prazo);
        return calendar.getTime();
    }

    public void createItensEmprestimo(List<ItemEmprestimo> itensEmprestimo, Long idEmprestimo, Date dataPrevista) {
        ItemEmprestimoDAO itemEmprestimoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getItemEmprestimoDAO();
        for (ItemEmprestimo item : itensEmprestimo) {
            item.setEmprestimo_id(idEmprestimo);
            item.setDataPrevista(dataPrevista);
            itemEmprestimoDAO.create(item);
        }
    }

    public List<ItemEmprestimo> readItensEmprestimoByEmprestimoId(Long emprestimoId) {
        ItemEmprestimoDAO itemEmprestimoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getItemEmprestimoDAO();
        List<ItemEmprestimo> itens = itemEmprestimoDAO.readByEmprestimoId(emprestimoId);
        
        for (ItemEmprestimo item : itens) {
            Livro livro = livroService.getLivroByItemEmprestimoId(item.getId());
            item.setLivro(livro);
        }

        return itens;
    }

    public ItemEmprestimo getItemEmprestimoByIsbn(String isbn, List<ItemEmprestimo> itens) {
        for (ItemEmprestimo item : itens) {
            if (livroService.getIsbn(item.getLivro()).equals(isbn)) {
                return item;
            }
        }
        return null;
    }

    public Livro getLivroByItemEmprestimo(ItemEmprestimo item) {
        return item.getLivro();
    }

    public Date getDataPrevista(ItemEmprestimo item) {
        return item.getDataPrevista();
    }

    public boolean updateDataDevolucaoItemEmprestimo(ItemEmprestimo item, Date dataDevolucao) {
        ItemEmprestimoDAO itemEmprestimoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getItemEmprestimoDAO();
        item.setDataDevolucao(dataDevolucao);
        return itemEmprestimoDAO.update(item) != null;
    }

    public List<ItemEmprestimo> processarItensEmprestimo(List<String> codigosLivros) {
        List<ItemEmprestimo> itens = new ArrayList<>();
        List<List<Livro>> livros = livroService.getLivrosByListOfIsbns(codigosLivros);

        for (int i = 0; i < livros.size(); i++) {
            List<Livro> listaLivros = livros.get(i);
            for (int j = 0; j < listaLivros.size(); j++) {
                Livro livro = listaLivros.get(j);
                if (livro.isDisponivel() && !livro.isExemplarBiblioteca()) {
                    ItemEmprestimo itemEmprestimo = instanciarItemEmprestimo(livro);
                    itens.add(itemEmprestimo);

                    Livro livroAtualizado = livroService.emprestarLivro(livro);
                    listaLivros.set(j, livroAtualizado);
                    break;
                }
            }
        }
        return itens;
    }
}
