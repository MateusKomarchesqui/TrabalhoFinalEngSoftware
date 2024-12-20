package com.example.trab_final.repository.factory;

import com.example.trab_final.repository.*;
import com.example.trab_final.repository.postgres.*;

public class PostgresDAOFactory extends DAOFactory {
    private static PostgresDAOFactory instance;

    private PostgresDAOFactory() {}

    public static synchronized PostgresDAOFactory getInstance() {
        if (instance == null) {
            instance = new PostgresDAOFactory();
        }
        return instance;
    }

    @Override
    public LivroDAO getLivroDAO() {
        return PostgresLivroDAO.getInstance();
    }

    @Override
    public AlunoDAO getAlunoDAO() {
        return PostgresAlunoDAO.getInstance();
    }

    @Override
    public TituloDAO getTituloDAO() {
        return PostgresTituloDAO.getInstance();
    }

    @Override
    public AreaDAO getAreaDAO() {
        return PostgresAreaDAO.getInstance();
    }

    @Override
    public AutorDAO getAutorDAO() {
        return PostgresAutorDAO.getInstance();
    }

    @Override
    public AutorTituloDAO getAutorTituloDAO() {
        return PostgresAutorTituloDAO.getInstance();
    }

    @Override
    public DebitoDAO getDebitoDAO() {
        return PostgresDebitoDAO.getInstance();
    }

    @Override
    public EmprestimoDAO getEmprestimoDAO() {
        return PostgresEmprestimoDAO.getInstance();
    }

    @Override
    public ItemEmprestimoDAO getItemEmprestimoDAO() {
        return PostgresItemEmprestimoDAO.getInstance();
    }
    
    @Override
    public DevolucaoDAO getDevolucaoDAO() {
        return PostgresDevolucaoDAO.getInstance();
    }

    @Override
    public ItemDevolucaoDAO getItemDevolucaoDAO() {
        return PostgresItemDevolucaoDAO.getInstance();
    }
}

