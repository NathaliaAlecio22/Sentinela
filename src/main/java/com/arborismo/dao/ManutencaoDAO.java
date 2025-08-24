package com.arborismo.dao;

import com.arborismo.model.Manutencao;
import com.arborismo.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO {
	public void adicionarManutencao(Manutencao manutencao) {
		String sql = "INSERT INTO Manutencoes (arvore_id, tipo, data_evento, descricao) VALUES (?, ?, ?, ?)";
		
		try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, manutencao.getArvoreId());
			stmt.setString(2, manutencao.getTipo());
			stmt.setDate(3, Date.valueOf(manutencao.getDataEvento()));
			stmt.setString(4, manutencao.getDescricao());
			
			stmt.executeUpdate();
			System.out.println("Manutenção registrada com sucesso para a árvore ID " + manutencao.getArvoreId());
			
			
		}catch(SQLException e) {
			System.err.println("Erro ao adicionar manutenção: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Manutencao> listarManutencoesPorArvore(int arvoresId){
		String sql = "SELECT * FROM Manutencoes WHERE arvore_id = ? ORDER BY data_evento DESC";
		List<Manutencao> manutencoes = new ArraysList<>();
		
		try(Connection conn = ConnectionFactory.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, arvoresId);
			
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					Manutencao manutencao = new Manutencao();
					manutencao.setId(rs.getInt("id"));
					manutencao.setArvoreId(rs.getInt("arvores_id"));
					manutencao.setTipo(rs.getString("tipo"));
					manutencao.setDataEvento(rs.getDate("data_evento").toLocalDate());
					manutencao.setDescricao(rs.getString("descricao"));
					manutencoes.add(manutencao);
				}
			}
		}catch(SQLException e) {
			System.err.println("Erro ao listar manutenções: " + e.getMessage());
			e.printStackTrace();
		}
		return manutencoes;
	}
	
	
}
