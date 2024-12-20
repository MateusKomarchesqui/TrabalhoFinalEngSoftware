package com.example.trab_final.service;

import org.springframework.stereotype.Service;

import com.example.trab_final.model.Area;
import com.example.trab_final.model.Autor;
import com.example.trab_final.model.Titulo;
import com.example.trab_final.repository.TituloDAO;
import com.example.trab_final.repository.factory.DAOFactory;
import com.example.trab_final.model.AutorTitulo;

import java.util.List;

@Service
public class TituloService {

    private final AutorService autorService;
    private final AreaService areaService;
    private final AutorTituloService autorTituloService;

    public TituloService(AutorService autorService, AreaService areaService, AutorTituloService autorTituloService) {
        this.autorService = autorService;
        this.areaService = areaService;
        this.autorTituloService = autorTituloService;
    }

    public boolean validarTitulo(String isbn) {
        TituloDAO tituloDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getTituloDAO();
        Titulo titulo = tituloDAO.readByIsbn(isbn);
        return titulo != null;
    }

    public boolean cadastrarTitulo(int prazo, String isbn, int edicao, int ano, String editora, int paginas, String nome, String area, List<List<String>> autores) {
        TituloDAO tituloDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getTituloDAO();
        
        // Autores do titulo
        List<Autor> autoresList = autorService.readByListNomes(autores);
        
        // Area do titulo
        Area areaObj = areaService.readByNome(area);

        // Titulo
        Titulo titulo = new Titulo(prazo, isbn, edicao, ano, editora, paginas, nome, areaObj, autoresList);
        titulo = tituloDAO.create(titulo);

        if (titulo == null) {
            return false;
        }

        for (Autor autor : autoresList) {
            AutorTitulo autorTitulo = new AutorTitulo(autor, titulo);
            boolean autorTituloValido = autorTituloService.create(autorTitulo);
            if (!autorTituloValido) {
                return false;
            }
        }
        
        return true;
    }

    public Titulo getTituloByIsbn(String isbn) {
        TituloDAO tituloDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getTituloDAO();
        Titulo titulo = tituloDAO.readByIsbn(isbn);

        Area area = areaService.getAreaByIsbn(isbn);
        titulo.setArea(area);

        List<Autor> autores = autorService.getAutoresByTituloIsbn(isbn);
        titulo.setAutores(autores);

        return titulo;
    }

    public Titulo getTituloByLivroId(Long livroId) {
        TituloDAO tituloDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getTituloDAO();
        Titulo titulo = tituloDAO.readTituloByLivroId(livroId);

        List<Autor> autores = autorService.getAutoresByTituloIsbn(titulo.getIsbn());
        titulo.setAutores(autores);

        Area area = areaService.getAreaByIsbn(titulo.getIsbn());
        titulo.setArea(area);

        return titulo;
    }

    public String getIsbn(Titulo titulo) {
        return titulo.getIsbn();
    }
}
