package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Titulo;
import com.example.trab_final.repository.TituloDAO;
import com.example.trab_final.connection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresTituloDAO implements TituloDAO {

    private static PostgresTituloDAO instance;

    private PostgresTituloDAO() {}

    public static synchronized PostgresTituloDAO getInstance() {
        if (instance == null) {
            instance = new PostgresTituloDAO();
        }
        return instance;
    }

    @Override
    public List<Titulo> readAll() {
        String sql = """
            SELECT t.prazo as titulo_prazo, t.isbn as titulo_isbn, t.edicao as titulo_edicao, t.ano as titulo_ano, t.editora as titulo_editora, t.paginas as titulo_paginas, t.nome as titulo_nome, t.area_nome as area_nome
            FROM titulo t
            """;

        List<Titulo> titulos = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Titulo titulo = new Titulo();
                titulo.setPrazo(rs.getInt("titulo_prazo"));
                titulo.setIsbn(rs.getString("titulo_isbn"));
                titulo.setEdicao(rs.getInt("titulo_edicao"));
                titulo.setAno(rs.getInt("titulo_ano"));
                titulo.setEditora(rs.getString("titulo_editora"));
                titulo.setPaginas(rs.getInt("titulo_paginas"));
                titulo.setNome(rs.getString("titulo_nome"));
                titulo.setArea(null);
                titulo.setAutores(null);

                titulos.add(titulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titulos;
    }

    @Override
    public Titulo readById(Long codigo) {
        String sql = """
            SELECT t.prazo as titulo_prazo, t.isbn as titulo_isbn, t.edicao as titulo_edicao, t.ano as titulo_ano, t.editora as titulo_editora, t.paginas as titulo_paginas, t.nome as titulo_nome, t.area_nome as area_nome
            FROM titulo t
            WHERE t.isbn = ?
            """;

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Titulo titulo = new Titulo();
                titulo.setPrazo(rs.getInt("titulo_prazo"));
                titulo.setIsbn(rs.getString("titulo_isbn"));
                titulo.setEdicao(rs.getInt("titulo_edicao"));
                titulo.setAno(rs.getInt("titulo_ano"));
                titulo.setEditora(rs.getString("titulo_editora"));
                titulo.setPaginas(rs.getInt("titulo_paginas"));
                titulo.setNome(rs.getString("titulo_nome"));
                titulo.setArea(null);
                titulo.setAutores(null);

                return titulo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Titulo create(Titulo titulo) {

        String sql = "INSERT INTO titulo (isbn, nome, prazo, edicao, ano, editora, paginas, area_nome) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, titulo.getIsbn());
            stmt.setString(2, titulo.getNome());
            stmt.setInt(3, titulo.getPrazo());
            stmt.setInt(4, titulo.getEdicao());
            stmt.setInt(5, titulo.getAno());
            stmt.setString(6, titulo.getEditora());
            stmt.setInt(7, titulo.getPaginas());
            stmt.setString(8, titulo.getArea().getNome());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titulo;
    }

    @Override
    public boolean delete(Titulo titulo) {
        String sql = "DELETE FROM titulo WHERE isbn = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, titulo.getIsbn());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Titulo update(Titulo titulo) {
        String sql = "UPDATE titulo SET nome = ?, prazo = ?, edicao = ?, ano = ?, editora = ?, paginas = ?, area_nome = ? WHERE isbn = ?";
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, titulo.getNome());
            stmt.setInt(2, titulo.getPrazo());
            stmt.setInt(3, titulo.getEdicao());
            stmt.setInt(4, titulo.getAno());
            stmt.setString(5, titulo.getEditora());
            stmt.setInt(6, titulo.getPaginas());
            stmt.setString(7, titulo.getArea().getNome());
            stmt.setString(8, titulo.getIsbn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titulo;
    }

    @Override
    public Titulo readByIsbn(String isbn) {
        String sql = """
            SELECT t.prazo as titulo_prazo, t.isbn as titulo_isbn, t.edicao as titulo_edicao, t.ano as titulo_ano, t.editora as titulo_editora, t.paginas as titulo_paginas, t.nome as titulo_nome, a.nome as area_nome
            FROM titulo t 
            JOIN area a ON t.area_nome = a.nome
            WHERE t.isbn = ?
            """;

        Titulo titulo = null;
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                titulo = new Titulo();
                titulo.setPrazo(rs.getInt("titulo_prazo"));
                titulo.setIsbn(rs.getString("titulo_isbn"));
                titulo.setEdicao(rs.getInt("titulo_edicao"));
                titulo.setAno(rs.getInt("titulo_ano"));
                titulo.setEditora(rs.getString("titulo_editora"));
                titulo.setPaginas(rs.getInt("titulo_paginas"));
                titulo.setNome(rs.getString("titulo_nome"));
                titulo.setArea(null);
                titulo.setAutores(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titulo;
    }

    @Override
    public Titulo readTituloByLivroId(Long livroId) {
        String sql = """
            SELECT t.prazo as titulo_prazo, t.isbn as titulo_isbn, t.edicao as titulo_edicao, t.ano as titulo_ano, t.editora as titulo_editora, t.paginas as titulo_paginas, t.nome as titulo_nome
            FROM titulo t
            JOIN livro l ON t.isbn = l.titulo_isbn
            WHERE l.id = ?
            """;

        Titulo titulo = null;
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, livroId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                titulo = new Titulo();
                titulo.setPrazo(rs.getInt("titulo_prazo"));
                titulo.setIsbn(rs.getString("titulo_isbn"));
                titulo.setEdicao(rs.getInt("titulo_edicao"));
                titulo.setAno(rs.getInt("titulo_ano"));
                titulo.setEditora(rs.getString("titulo_editora"));
                titulo.setPaginas(rs.getInt("titulo_paginas"));
                titulo.setNome(rs.getString("titulo_nome"));
                titulo.setArea(null);
                titulo.setAutores(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titulo;
    }

}
