package com.arborismo.dao;

import com.arborismo.model.Arvore;
import com.arborismo.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ArvoreDAO {

    public void adicionarArvore(Arvore arvore) {
        String sql = "INSERT INTO Arvores (projeto_id, codigo_qr, nome_popular, nome_cientifico, numero_plaqueta, localizacao, altura_m, dap_cm, idade_estimada, inclinacao_tronco, raizes_expostas, forma_copa, pragas_doencas, oco_tronco, rachaduras_fissuras, risco_galhos, data_ultima_poda, tipo_ultima_poda, historico_quedas, proximidade_risco, avaliacao_risco, data_inspecao, responsavel_inspecao, observacoes_adicionais, situacao_recomendada, proxima_inspecao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arvore.getProjetoId());
            stmt.setString(2, arvore.getCodigoQr());
            stmt.setString(3, arvore.getNomePopular());
            stmt.setString(4, arvore.getNomeCientifico());
            stmt.setString(5, arvore.getNumeroPlaqueta());
            stmt.setString(6, arvore.getLocalizacao());
            stmt.setBigDecimal(7, arvore.getAlturaM());
            stmt.setBigDecimal(8, arvore.getDapCm());
            stmt.setInt(9, arvore.getIdadeEstimada());
            stmt.setString(10, arvore.getInclinacaoTronco());
            stmt.setBoolean(11, arvore.isRaizesExpostas());
            stmt.setString(12, arvore.getFormaCopa());
            stmt.setString(13, arvore.getPragasDoencas());
            stmt.setString(14, arvore.getOcoTronco());
            stmt.setString(15, arvore.getRachadurasFissuras());
            stmt.setString(16, arvore.getRiscoGalhos());
            stmt.setDate(17, arvore.getDataUltimaPoda() != null ? Date.valueOf(arvore.getDataUltimaPoda()) : null);
            stmt.setString(18, arvore.getTipoUltimaPoda());
            stmt.setString(19, arvore.getHistoricoQuedas());
            stmt.setString(20, arvore.getProximidadeRisco());
            stmt.setString(21, arvore.getAvaliacaoRisco());
            stmt.setDate(22, arvore.getDataInspecao() != null ? Date.valueOf(arvore.getDataInspecao()) : null);
            stmt.setString(23, arvore.getResponsavelInspecao());
            stmt.setString(24, arvore.getObservacoesAdicionais());
            stmt.setString(25, arvore.getSituacaoRecomendada());
            stmt.setDate(26, arvore.getProximaInspecao() != null ? Date.valueOf(arvore.getProximaInspecao()) : null);
            
            stmt.executeUpdate();
            System.out.println("Árvore '" + arvore.getNomePopular() + "' adicionada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar árvore: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Arvore> listarArvoresPorProjeto(int projetoId) {
        String sql = "SELECT * FROM Arvores WHERE projeto_id = ?";
        List<Arvore> arvores = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, projetoId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    arvores.add(criarArvoreDoResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar árvores por projeto: " + e.getMessage());
            e.printStackTrace();
        }
        return arvores;
    }

    public Arvore buscarArvorePorCodigo(String codigoQr) {
        String sql = "SELECT * FROM Arvores WHERE codigo_qr = ?";
        Arvore arvore = null;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigoQr);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    arvore = criarArvoreDoResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar árvore por código QR: " + e.getMessage());
            e.printStackTrace();
        }
        return arvore;
    }

    // --- NOVOS MÉTODOS ---
    public boolean atualizarArvore(Arvore arvore) {
        String sql = "UPDATE Arvores SET nome_popular=?, nome_cientifico=?, numero_plaqueta=?, localizacao=?, altura_m=?, dap_cm=?, idade_estimada=?, inclinacao_tronco=?, raizes_expostas=?, forma_copa=?, pragas_doencas=?, oco_tronco=?, rachaduras_fissuras=?, risco_galhos=?, data_ultima_poda=?, tipo_ultima_poda=?, historico_quedas=?, proximidade_risco=?, avaliacao_risco=?, data_inspecao=?, responsavel_inspecao=?, observacoes_adicionais=?, situacao_recomendada=?, proxima_inspecao=? WHERE id=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, arvore.getNomePopular());
            stmt.setString(2, arvore.getNomeCientifico());
            stmt.setString(3, arvore.getNumeroPlaqueta());
            stmt.setString(4, arvore.getLocalizacao());
            stmt.setBigDecimal(5, arvore.getAlturaM());
            stmt.setBigDecimal(6, arvore.getDapCm());
            stmt.setInt(7, arvore.getIdadeEstimada());
            stmt.setString(8, arvore.getInclinacaoTronco());
            stmt.setBoolean(9, arvore.isRaizesExpostas());
            stmt.setString(10, arvore.getFormaCopa());
            stmt.setString(11, arvore.getPragasDoencas());
            stmt.setString(12, arvore.getOcoTronco());
            stmt.setString(13, arvore.getRachadurasFissuras());
            stmt.setString(14, arvore.getRiscoGalhos());
            stmt.setDate(15, arvore.getDataUltimaPoda() != null ? Date.valueOf(arvore.getDataUltimaPoda()) : null);
            stmt.setString(16, arvore.getTipoUltimaPoda());
            stmt.setString(17, arvore.getHistoricoQuedas());
            stmt.setString(18, arvore.getProximidadeRisco());
            stmt.setString(19, arvore.getAvaliacaoRisco());
            stmt.setDate(20, arvore.getDataInspecao() != null ? Date.valueOf(arvore.getDataInspecao()) : null);
            stmt.setString(21, arvore.getResponsavelInspecao());
            stmt.setString(22, arvore.getObservacoesAdicionais());
            stmt.setString(23, arvore.getSituacaoRecomendada());
            stmt.setDate(24, arvore.getProximaInspecao() != null ? Date.valueOf(arvore.getProximaInspecao()) : null);
            stmt.setInt(25, arvore.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar árvore: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean removerArvore(int id) {
        String sql = "DELETE FROM Arvores WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover árvore: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Arvore criarArvoreDoResultSet(ResultSet rs) throws SQLException {
        Arvore arvore = new Arvore();
        arvore.setId(rs.getInt("id"));
        arvore.setProjetoId(rs.getInt("projeto_id"));
        arvore.setCodigoQr(rs.getString("codigo_qr"));
        arvore.setNomePopular(rs.getString("nome_popular"));
        arvore.setNomeCientifico(rs.getString("nome_cientifico"));
        arvore.setNumeroPlaqueta(rs.getString("numero_plaqueta"));
        arvore.setLocalizacao(rs.getString("localizacao"));
        arvore.setAlturaM(rs.getBigDecimal("altura_m"));
        arvore.setDapCm(rs.getBigDecimal("dap_cm"));
        arvore.setIdadeEstimada(rs.getInt("idade_estimada"));
        arvore.setInclinacaoTronco(rs.getString("inclinacao_tronco"));
        arvore.setRaizesExpostas(rs.getBoolean("raizes_expostas"));
        arvore.setFormaCopa(rs.getString("forma_copa"));
        arvore.setPragasDoencas(rs.getString("pragas_doencas"));
        arvore.setOcoTronco(rs.getString("oco_tronco"));
        arvore.setRachadurasFissuras(rs.getString("rachaduras_fissuras"));
        arvore.setRiscoGalhos(rs.getString("risco_galhos"));
        arvore.setDataUltimaPoda(rs.getDate("data_ultima_poda") != null ? rs.getDate("data_ultima_poda").toLocalDate() : null);
        arvore.setTipoUltimaPoda(rs.getString("tipo_ultima_poda"));
        arvore.setHistoricoQuedas(rs.getString("historico_quedas"));
        arvore.setProximidadeRisco(rs.getString("proximidade_risco"));
        arvore.setAvaliacaoRisco(rs.getString("avaliacao_risco"));
        arvore.setDataInspecao(rs.getDate("data_inspecao") != null ? rs.getDate("data_inspecao").toLocalDate() : null);
        arvore.setResponsavelInspecao(rs.getString("responsavel_inspecao"));
        arvore.setObservacoesAdicionais(rs.getString("observacoes_adicionais"));
        arvore.setSituacaoRecomendada(rs.getString("situacao_recomendada"));
        arvore.setProximaInspecao(rs.getDate("proxima_inspecao") != null ? rs.getDate("proxima_inspecao").toLocalDate() : null);
        return arvore;
    }
}