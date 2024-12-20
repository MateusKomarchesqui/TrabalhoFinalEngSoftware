package com.example.trab_final.service;

import com.example.trab_final.dto.AutorDTO;
import com.example.trab_final.model.Autor;
import com.example.trab_final.repository.AutorDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AutorService {

    public AutorService() {
    }

    public boolean validarAutor(String nome, String sobrenome) {
        AutorDAO autorDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAutorDAO();
        return autorDAO.readByNome(nome, sobrenome) != null;
    }

    public boolean cadastrarAutores(List<List<String>> autores) {
        AutorDAO autorDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAutorDAO();
        List<Autor> autoresList = new ArrayList<>();

        for (List<String> autor : autores) {
            String autorNome = autor.get(0);
            String autorSobrenome = String.join(" ", autor.subList(1, autor.size()));

            Autor autorObj = autorDAO.readByNome(autorNome, autorSobrenome);

            if (autorObj == null) {
                autorObj = new Autor(autorNome, autorSobrenome, "");
                autorObj = autorDAO.create(autorObj);
            }

            autoresList.add(autorObj);
        }

        return autoresList.size() == autores.size();
    }

    public boolean cadastrarAutor(String nome, String sobrenome, String titulacao) {
        Autor autor = new Autor(nome, sobrenome, titulacao);
        AutorDAO autorDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAutorDAO();
        return autorDAO.create(autor) != null;
    }

    public List<Autor> readByListNomes(List<List<String>> autores) {
        AutorDAO autorDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAutorDAO();
        List<Autor> autoresList = new ArrayList<>();

        for (List<String> autor : autores) {
            String autorNome = autor.get(0);
            String autorSobrenome = String.join(" ", autor.subList(1, autor.size()));

            Autor autorObj = autorDAO.readByNome(autorNome, autorSobrenome);

            if (autorObj == null) {
                autorObj = new Autor(autorNome, autorSobrenome, "");
                autorObj = autorDAO.create(autorObj);
            }

            autoresList.add(autorObj);
        }
        return autoresList;
    }

    public List<List<String>> converterAutoresParaLista(List<AutorDTO> autoresDTO) {
        List<List<String>> autores = new ArrayList<>();
        for (AutorDTO autor : autoresDTO) {
            autores.add(autor.toList());
        }
        return autores;
    }

    public List<Autor> getAutoresByTituloIsbn(String isbn) {
        AutorDAO autorDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAutorDAO();
        return autorDAO.readAutoresByTituloIsbn(isbn);
    }
}
