package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Livro;
import com.example.trab_final.repository.LivroDAO;
import com.example.trab_final.connection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresLivroDAO implements LivroDAO {

    private static PostgresLivroDAO instance;

    private PostgresLivroDAO() {}

    public static synchronized PostgresLivroDAO getInstance() {
        if (instance == null) {
            instance = new PostgresLivroDAO();
        }
        return instance;
    }

    @Override
    public Livro readById(Long id) {
        String sql = """
            SELECT l.id as l_id, l.disponivel as l_disponivel, l.exemplar_biblioteca as l_exemplar, l.titulo_isbn as l_isbn FROM public.livro l
            WHERE l.id = ?
        """;

        Livro livro = null;
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getLong("l_id"));
                livro.setDisponivel(rs.getBoolean("l_disponivel"));
                livro.setExemplarBiblioteca(rs.getBoolean("l_exemplar"));
                livro.setTitulo(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }    

    @Override
    public List<Livro> readAll() {
        String sql = """
            SELECT l.id as l_id, l.disponivel as l_disponivel, l.exemplar_biblioteca as l_exemplar, l.titulo_isbn as l_isbn FROM public.livro l
        """;

        List<Livro> livros = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("l_id"));
                livro.setDisponivel(rs.getBoolean("l_disponivel"));
                livro.setExemplarBiblioteca(rs.getBoolean("l_exemplar"));
                livro.setTitulo(null);
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
    
    @Override
    public Livro create(Livro livro) {
        String sql = "INSERT INTO public.livro (disponivel, exemplar_biblioteca, titulo_isbn) VALUES (?, ?, ?) returning id";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setBoolean(1, livro.isDisponivel());
            stmt.setBoolean(2, livro.isExemplarBiblioteca());
            stmt.setString(3, livro.getTitulo().getIsbn());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return livro;
    }

    @Override
    public Livro update(Livro livro) {
        String sql = "UPDATE public.livro SET disponivel = ?, exemplar_biblioteca = ?, titulo_isbn = ? WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setBoolean(1, livro.isDisponivel());
            stmt.setBoolean(2, livro.isExemplarBiblioteca());
            stmt.setString(3, livro.getTitulo().getIsbn());
            stmt.setLong(4, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return livro;
    }

    @Override
    public boolean delete(Livro livro) {
        String sql = "DELETE FROM public.livro WHERE id = ?";
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, livro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Livro> readByIsbnBasic(String isbn) {
        String sql = """
            SELECT l.id as l_id, l.disponivel as l_disponivel, l.exemplar_biblioteca as l_exemplar, l.titulo_isbn as l_isbn FROM public.livro l
            WHERE l.titulo_isbn = ?
        """;
    
        List<Livro> livros = new ArrayList<>();
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("l_id"));
                livro.setDisponivel(rs.getBoolean("l_disponivel"));
                livro.setExemplarBiblioteca(rs.getBoolean("l_exemplar"));
                livro.setTitulo(null);
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    @Override
    public Livro readByItemEmprestimoId(Long id) {
        String sql = """
            SELECT l.id as l_id, l.disponivel as l_disponivel, l.exemplar_biblioteca as l_exemplar FROM public.livro l
            JOIN public.item_emprestimo ie ON l.id = ie.livro_id
            WHERE ie.id = ?
        """;

        Livro livro = null;
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getLong("l_id"));
                livro.setDisponivel(rs.getBoolean("l_disponivel"));
                livro.setExemplarBiblioteca(rs.getBoolean("l_exemplar"));
                livro.setTitulo(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    @Override
    public Livro readByItemDevolucaoId(Long id) {
        String sql = """
            SELECT 
                l.id as l_id, 
                l.disponivel as l_disponivel, 
                l.exemplar_biblioteca as l_exemplar 
            FROM 
                Item_Devolucao id
            JOIN 
                Livro l
            ON 
                id.livro_id = l.id
            WHERE 
                id.id = ?
        """;


        Livro livro = null;
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getLong("l_id"));
                livro.setDisponivel(rs.getBoolean("l_disponivel"));
                livro.setExemplarBiblioteca(rs.getBoolean("l_exemplar"));
                livro.setTitulo(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }
    
}
