package com.arborismo.dao;

import com.arborismo.model.Projeto;
import com.arborismo.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public void adicionarProjeto(Projeto projeto) {
        String sql = "INSERT INTO Projetos (empresa_id, nome_projeto, descricao_projeto, data_criacao) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, projeto.getEmpresaId());
            stmt.setString(2, projeto.getNomeProjeto());
            stmt.setString(3, projeto.getDescricaoProjeto());
            stmt.setDate(4, Date.valueOf(projeto.getDataCriacao()));

            stmt.executeUpdate();
            System.out.println("Projeto '" + projeto.getNomeProjeto() + "' adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar projeto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Projeto> listarProjetos(int empresaId) {
        String sql = "SELECT * FROM Projetos WHERE empresa_id = ?";
        List<Projeto> projetos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empresaId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Projeto projeto = new Projeto();
                    projeto.setId(rs.getInt("id"));
                    projeto.setEmpresaId(rs.getInt("empresa_id"));
                    projeto.setNomeProjeto(rs.getString("nome_projeto"));
                    projeto.setDescricaoProjeto(rs.getString("descricao_projeto"));
                    projeto.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                    projetos.add(projeto);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
            e.printStackTrace();
        }
        return projetos;
    }
}