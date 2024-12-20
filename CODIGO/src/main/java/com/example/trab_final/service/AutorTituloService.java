package com.example.trab_final.service;

import com.example.trab_final.model.AutorTitulo;
import com.example.trab_final.repository.AutorTituloDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import org.springframework.stereotype.Service;

@Service
public class AutorTituloService {

    public AutorTituloService() {
    }

    public boolean create(AutorTitulo autorTitulo) {
        AutorTituloDAO autorTituloDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAutorTituloDAO();
        return autorTituloDAO.create(autorTitulo) != null;
    }
}
