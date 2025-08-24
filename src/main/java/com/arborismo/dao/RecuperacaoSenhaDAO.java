package com.arborismo.dao;

import com.arborismo.model.RecuperacaoSenha;
import com.arborismo.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;

public class RecuperacaoSenhaDAO {

    public void criarToken(RecuperacaoSenha recuperacaoSenha) {
        String sql = "INSERT INTO RecuperacaoSenha (empresa_id, token, data_expiracao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recuperacaoSenha.getEmpresaId());
            stmt.setString(2, recuperacaoSenha.getToken());
            stmt.setTimestamp(3, Timestamp.valueOf(recuperacaoSenha.getDataExpiracao()));

            stmt.executeUpdate();
            System.out.println("Token de recuperação criado para a empresa ID " + recuperacaoSenha.getEmpresaId());

        } catch (SQLException e) {
            System.err.println("Erro ao criar token de recuperação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public RecuperacaoSenha buscarToken(String token) {
        String sql = "SELECT * FROM RecuperacaoSenha WHERE token = ? AND data_expiracao > ?";
        RecuperacaoSenha recuperacaoSenha = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, token);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    recuperacaoSenha = new RecuperacaoSenha();
                    recuperacaoSenha.setId(rs.getInt("id"));
                    recuperacaoSenha.setEmpresaId(rs.getInt("empresa_id"));
                    recuperacaoSenha.setToken(rs.getString("token"));
                    recuperacaoSenha.setDataExpiracao(rs.getTimestamp("data_expiracao").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar token de recuperação: " + e.getMessage());
            e.printStackTrace();
        }
        return recuperacaoSenha;
    }
}