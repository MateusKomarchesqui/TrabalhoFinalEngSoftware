package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.AutorTitulo;
import com.example.trab_final.repository.AutorTituloDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.trab_final.connection.PostgresConnection;

import java.util.List;

public class PostgresAutorTituloDAO implements AutorTituloDAO {
    private static PostgresAutorTituloDAO instance;

    private PostgresAutorTituloDAO() {}

    public static synchronized PostgresAutorTituloDAO getInstance() {
        if (instance == null) {
            instance = new PostgresAutorTituloDAO();
        }
        return instance;
    }

    @Override
    public AutorTitulo readById(Long id) {
        return null;
    }

    @Override
    public List<AutorTitulo> readAll() {
        return null;
    }

    @Override
    public AutorTitulo create(AutorTitulo obj) {
        String sql = "INSERT INTO autor_titulo (autor_id, titulo_isbn) VALUES (?, ?)";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getAutor().getId());
            stmt.setString(2, obj.getTitulo().getIsbn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public AutorTitulo update(AutorTitulo obj) {
        return null;
    }

    @Override
    public boolean delete(AutorTitulo obj) {
        return false;
    }

}
