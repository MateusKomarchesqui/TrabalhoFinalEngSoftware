package com.example.trab_final.service;

import org.springframework.stereotype.Service;

import com.example.trab_final.model.Livro;
import com.example.trab_final.model.Titulo;
import com.example.trab_final.repository.LivroDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import java.util.List;
import java.util.ArrayList;

@Service
public class LivroService {

    private final TituloService tituloService;

    public LivroService(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    public List<Livro> getLivrosByIsbn(String isbn) {
        LivroDAO livroDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getLivroDAO();
        List<Livro> livros = livroDAO.readByIsbnBasic(isbn);
        Titulo titulo = tituloService.getTituloByIsbn(isbn);

        for (Livro livro : livros) {
            livro.setTitulo(titulo);
        }
        return livros;
    }

    public List<List<Livro>> getLivrosByListOfIsbns(List<String> isbns) {
        List<List<Livro>> livros = new ArrayList<>();
        for (String isbn : isbns) {
            List<Livro> livrosIsbn = getLivrosByIsbn(isbn);
            livros.add(livrosIsbn);
        }
        return livros;
    }

    public Livro emprestarLivro(Livro livro) {
        LivroDAO livroDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getLivroDAO();
        livro.setDisponivel(false);
        return livroDAO.update(livro);
    }

    public Livro devolverLivro(Livro livro) {
        LivroDAO livroDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getLivroDAO();
        livro.setDisponivel(true);
        return livroDAO.update(livro);
    }

    public int getPrazo(Livro livro) {
        return livro.getTitulo().getPrazo();
    }

    public boolean cadastrarLivro(boolean disponivel, boolean exemplarBiblioteca, String isbn) {
        LivroDAO livroDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getLivroDAO();
        
        Titulo titulo = tituloService.getTituloByIsbn(isbn);
        if (titulo == null) {
            return false;
        }

        Livro livro = new Livro(disponivel, exemplarBiblioteca, titulo);
        livro = livroDAO.create(livro);

        return livro != null;
    }

    public Livro getLivroByItemEmprestimoId(Long itemEmprestimoId) {
        LivroDAO livroDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getLivroDAO();
        Livro livro = livroDAO.readByItemEmprestimoId(itemEmprestimoId);

        Titulo titulo = tituloService.getTituloByLivroId(livro.getId());
        livro.setTitulo(titulo);

        return livro;
    }

    public Livro getLivroByItemDevolucaoId(Long itemDevolucaoId) {
        LivroDAO livroDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getLivroDAO();
        Livro livro = livroDAO.readByItemDevolucaoId(itemDevolucaoId);

        Titulo titulo = tituloService.getTituloByLivroId(livro.getId());
        livro.setTitulo(titulo);

        return livro;
    }

    public String getIsbn(Livro livro) {
        return tituloService.getIsbn(livro.getTitulo());
    }
}
