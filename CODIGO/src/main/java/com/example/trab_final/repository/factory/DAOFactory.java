package com.example.trab_final.repository.factory;

import com.example.trab_final.repository.*;

public abstract class DAOFactory {

    public static final int POSTGRES = 1;
    public static final int ORACLE = 2;
    public static final int MYSQL = 3;

    public abstract AlunoDAO getAlunoDAO();
    public abstract AreaDAO getAreaDAO();
    public abstract AutorDAO getAutorDAO();
    public abstract AutorTituloDAO getAutorTituloDAO();
    public abstract DebitoDAO getDebitoDAO();
    public abstract EmprestimoDAO getEmprestimoDAO();
    public abstract ItemEmprestimoDAO getItemEmprestimoDAO();
    public abstract LivroDAO getLivroDAO();
    public abstract TituloDAO getTituloDAO();
    public abstract DevolucaoDAO getDevolucaoDAO();
    public abstract ItemDevolucaoDAO getItemDevolucaoDAO();


    public static DAOFactory getDAOFactory(int type) {
        switch (type) {
            case POSTGRES:
                return PostgresDAOFactory.getInstance();
            case MYSQL:
                // return MySQLDAOFactory.getInstance();
                return null;
            case ORACLE:
                // return OracleDAOFactory.getInstance();
                return null;
            default:
                throw new IllegalArgumentException("Tipo de fábrica desconhecido.");
        }
    }
}
