package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.ItemEmprestimo;
import com.example.trab_final.repository.ItemEmprestimoDAO;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

import com.example.trab_final.connection.PostgresConnection;

public class PostgresItemEmprestimoDAO implements ItemEmprestimoDAO{

    private static PostgresItemEmprestimoDAO instance;

    private PostgresItemEmprestimoDAO() {}

    public static PostgresItemEmprestimoDAO getInstance() {
        if(instance == null) {
            instance = new PostgresItemEmprestimoDAO();
        }
        return instance;
    }

    @Override
    public ItemEmprestimo readById(Long id) {
        String sql = "SELECT * FROM public.item_emprestimo WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ItemEmprestimo item = new ItemEmprestimo();
                item.setId(rs.getLong("id"));
                item.setEmprestimo_id(rs.getLong("emprestimo_id"));
                item.setLivro(null);
                item.setDataDevolucao(rs.getDate("data_devolucao"));
                item.setDataPrevista(rs.getDate("data_prevista"));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ItemEmprestimo> readAll() {
        String sql = "SELECT * FROM public.item_emprestimo";

        List<ItemEmprestimo> itens = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemEmprestimo item = new ItemEmprestimo();
                item.setId(rs.getLong("id"));
                item.setEmprestimo_id(rs.getLong("emprestimo_id"));
                item.setLivro(null);
                item.setDataDevolucao(rs.getDate("data_devolucao"));
                item.setDataPrevista(rs.getDate("data_prevista"));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    @Override
    public ItemEmprestimo create(ItemEmprestimo obj) {
        String sql = "INSERT INTO public.item_emprestimo (emprestimo_id, livro_id, data_devolucao, data_prevista) VALUES (?, ?, ?, ?) returning id";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getEmprestimo_id());
            stmt.setLong(2, obj.getLivro().getId());
            if (obj.getDataDevolucao() == null) {
                stmt.setNull(3, java.sql.Types.DATE);
            } else {
                stmt.setDate(3, new java.sql.Date(obj.getDataDevolucao().getTime()));
            }
            stmt.setDate(4, new java.sql.Date(obj.getDataPrevista().getTime()));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                obj.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public ItemEmprestimo update(ItemEmprestimo obj) {
        String sql = "UPDATE public.item_emprestimo SET emprestimo_id = ?, livro_id = ?, data_devolucao = ?, data_prevista = ? WHERE id = ?";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getEmprestimo_id());
            stmt.setLong(2, obj.getLivro().getId());
            if (obj.getDataDevolucao() == null) {
                stmt.setNull(3, java.sql.Types.DATE);
            } else {
                stmt.setDate(3, new java.sql.Date(obj.getDataDevolucao().getTime()));
            }
            stmt.setDate(4, new java.sql.Date(obj.getDataPrevista().getTime()));
            stmt.setLong(5, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }


    @Override
    public boolean delete(ItemEmprestimo obj) {
        String sql = "DELETE FROM public.item_emprestimo WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ItemEmprestimo> readByEmprestimoId(long emprestimo_id) {
        String sql = "SELECT * FROM public.item_emprestimo WHERE emprestimo_id = ?";
        List<ItemEmprestimo> itens = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, emprestimo_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemEmprestimo item = new ItemEmprestimo();
                item.setId(rs.getLong("id"));
                item.setEmprestimo_id(rs.getLong("emprestimo_id"));
                item.setLivro(null);
                item.setDataDevolucao(rs.getDate("data_devolucao"));
                item.setDataPrevista(rs.getDate("data_prevista"));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }
}
