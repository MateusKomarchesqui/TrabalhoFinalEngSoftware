package com.example.trab_final.service;

import com.example.trab_final.model.Debito;
import com.example.trab_final.repository.DebitoDAO;
import com.example.trab_final.repository.factory.DAOFactory;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DebitoService {

    public DebitoService(){}

    public boolean verificarDebito(Long matricula) {
        DebitoDAO debitoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getDebitoDAO();
        return !debitoDAO.readByMatricula(matricula).isEmpty();
    }

    public boolean criarDebito(Long matricula, Date data, float valor) {
        DebitoDAO debitoDAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRES).getDebitoDAO();
        Debito debito = new Debito(matricula, data, valor);
        return debitoDAO.create(debito) != null;
    }

}
