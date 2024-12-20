package com.example.trab_final.repository.postgres;

import com.example.trab_final.model.Emprestimo;
import com.example.trab_final.repository.EmprestimoDAO;

import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.trab_final.connection.PostgresConnection;

public class PostgresEmprestimoDAO implements EmprestimoDAO {

    private static PostgresEmprestimoDAO instance;

    private PostgresEmprestimoDAO() {}

    public static PostgresEmprestimoDAO getInstance() {
        if(instance == null) {
            instance = new PostgresEmprestimoDAO();
        }
        return instance;
    }

    @Override
    public Emprestimo readById(Long id) {
        String sql = "SELECT * FROM public.emprestimo WHERE id = ?";
        
        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("id"));
                emprestimo.setMatricula(rs.getLong("matricula"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataPrevista(rs.getDate("data_prevista"));
                return emprestimo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emprestimo> readAll() {
        String sql = "SELECT * FROM public.emprestimo";

        List<Emprestimo> emprestimos = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("id"));
                emprestimo.setMatricula(rs.getLong("matricula"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataPrevista(rs.getDate("data_prevista"));

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    @Override
    public Emprestimo create(Emprestimo obj) {
        String sql = "INSERT INTO public.emprestimo (matricula, data_emprestimo, data_prevista) VALUES (?, ?, ?) returning id";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getMatricula());
            stmt.setDate(2, new java.sql.Date(obj.getDataEmprestimo().getTime()));
            stmt.setDate(3, new java.sql.Date(obj.getDataPrevista().getTime()));

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
    public Emprestimo update(Emprestimo obj) {
        String sql = "UPDATE public.emprestimo SET matricula = ?, data_emprestimo = ?, data_prevista = ? WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getMatricula());
            stmt.setDate(2, new java.sql.Date(obj.getDataEmprestimo().getTime()));
            stmt.setDate(3, new java.sql.Date(obj.getDataPrevista().getTime()));
            stmt.setLong(4, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public boolean delete(Emprestimo obj) {
        String sql = "DELETE FROM public.emprestimo WHERE id = ?";

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<Emprestimo> readByMatricula(long matricula) {
        String sql = "SELECT * FROM public.emprestimo WHERE matricula = ?";

        List<Emprestimo> emprestimos = new ArrayList<>();

        try (PreparedStatement stmt = PostgresConnection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, matricula);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getLong("id"));
                emprestimo.setMatricula(rs.getLong("matricula"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataPrevista(rs.getDate("data_prevista"));

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

}
