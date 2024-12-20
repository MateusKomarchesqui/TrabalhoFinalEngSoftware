package com.example.trab_final.service;

import org.springframework.stereotype.Service;

import com.example.trab_final.model.Area;
import com.example.trab_final.repository.AreaDAO;
import com.example.trab_final.repository.factory.DAOFactory;

@Service
public class AreaService {
    public AreaService() {
    }

    public boolean validarArea(String nome) {
        AreaDAO areaDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAreaDAO();
        return areaDAO.readByNome(nome) != null;
    }

    public Area readByNome(String nome) {
        AreaDAO areaDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAreaDAO();
        return areaDAO.readByNome(nome);
    }

    public boolean cadastrarArea(String nome, String descricao) {
        Area area = new Area(nome, descricao);
        AreaDAO areaDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAreaDAO();
        return areaDAO.create(area) != null;
    }

    public Area getAreaByIsbn(String isbn) {
        AreaDAO areaDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getAreaDAO();
        return areaDAO.readByIsbn(isbn);
    }
}
