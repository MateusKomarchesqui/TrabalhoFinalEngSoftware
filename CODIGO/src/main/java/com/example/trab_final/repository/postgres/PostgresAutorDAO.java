package com.example.trab_final.repository.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.trab_final.model.Autor;
import com.example.trab_final.repository.AutorDAO;
import com.example.trab_final.connection.PostgresConnection;

public class PostgresAutorDAO implements AutorDAO {

    private static PostgresAutorDAO instance;

    private PostgresAutorDAO() {}

    public static synchronized PostgresAutorDAO getInstance() {
        if (instance == null) {
            instance = new PostgresAutorDAO();
        }
        return instance;
    }

    @Override
    public List<Autor> readAll() {
        String sql = "SELECT * FROM autor";

        List<Autor> autores = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Autor autor = new Autor(rs.getLong("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("titulacao"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

    @Override
    public Autor readById(Long id) {
        String sql = "SELECT * FROM autor WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Autor(rs.getLong("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("titulacao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Autor create(Autor autor) {
        String sql = "INSERT INTO autor (nome, sobrenome, titulacao) VALUES (?, ?, ?) returning id";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getSobrenome());
            stmt.setString(3, autor.getTitulacao());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                autor.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return autor;
    }

    @Override
    public Autor update(Autor autor) {
        String sql = "UPDATE autor SET nome = ?, sobrenome = ?, titulacao = ? WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getSobrenome());
            stmt.setString(3, autor.getTitulacao());
            stmt.setLong(4, autor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autor;
    }

    @Override
    public boolean delete(Autor autor) {
        String sql = "DELETE FROM autor WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, autor.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Autor readByNome(String nome, String sobrenome) {
        String sql = "SELECT autor.id, autor.nome, autor.sobrenome, autor.titulacao FROM autor WHERE autor.nome = ? AND autor.sobrenome = ?";
        Autor autor = null;

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                autor = new Autor(rs.getLong("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("titulacao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autor;
    } 

    @Override
    public List<Autor> readAutoresByTituloIsbn(String isbn) {
        String sql = """
            SELECT 
                Autor.id,
                Autor.nome,
                Autor.sobrenome,
                Autor.titulacao
            FROM 
                Autor
            JOIN 
                Autor_Titulo 
            ON 
                Autor.id = Autor_Titulo.autor_id
            WHERE 
                Autor_Titulo.titulo_isbn = ?
        """;
    
        List<Autor> autores = new ArrayList<>();
    
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                long autorId = rs.getLong("id");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String titulacao = rs.getString("titulacao");
    
                Autor autor = new Autor(autorId, nome, sobrenome, titulacao);
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

}
