package com.arborismo.dao;

import com.arborismo.model.Empresa;
import com.arborismo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;



public class EmpresaDAO {

    public void adicionarEmpresa(Empresa empresa) {
        String sql = "INSERT INTO Empresas (razao_social, nome_fantasia, cnpj, inscricao_estadual, ramo_atividade, logradouro, numero, cidade, estado, cep, telefone, celular, email_principal, senha, site, nome_responsavel, cargo_responsavel, email_responsavel, funcionarios_campo, certificacoes, equipamentos, area_cobertura, parcerias) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empresa.getRazaoSocial());
            stmt.setString(2, empresa.getNomeFantasia());
            stmt.setString(3, empresa.getCnpj());
            stmt.setString(4, empresa.getInscricaoEstadual());
            stmt.setString(5, empresa.getRamoAtividade());
            stmt.setString(6, empresa.getLogradouro());
            stmt.setString(7, empresa.getNumero());
            stmt.setString(8, empresa.getCidade());
            stmt.setString(9, empresa.getEstado());
            stmt.setString(10, empresa.getCep());
            stmt.setString(11, empresa.getTelefone());
            stmt.setString(12, empresa.getCelular());
            stmt.setString(13, empresa.getEmailPrincipal());
            stmt.setString(14, empresa.getSenha());
            stmt.setString(15, empresa.getSite());
            stmt.setString(16, empresa.getNomeResponsavel());
            stmt.setString(17, empresa.getCargoResponsavel());
            stmt.setString(18, empresa.getEmailResponsavel());
            stmt.setInt(19, empresa.getFuncionariosCampo());
            stmt.setString(20, empresa.getCertificacoes());
            stmt.setString(21, empresa.getEquipamentos());
            stmt.setString(22, empresa.getAreaCobertura());
            stmt.setString(23, empresa.getParcerias());

            stmt.executeUpdate();
            System.out.println("Empresa '" + empresa.getNomeFantasia() + "' adicionada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar empresa: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ---MÉTODO PARA LOGIN ---
    public Empresa buscarPorEmail(String email) {
        String sql = "SELECT * FROM Empresas WHERE email_principal = ?";
        Empresa empresa = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empresa = new Empresa();
                    // Preenche o objeto Empresa com os dados do ResultSet
                    empresa.setId(rs.getInt("id"));
                    empresa.setRazaoSocial(rs.getString("razao_social"));
                    empresa.setNomeFantasia(rs.getString("nome_fantasia"));
                    empresa.setCnpj(rs.getString("cnpj"));
                    empresa.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                    empresa.setRamoAtividade(rs.getString("ramo_atividade"));
                    empresa.setLogradouro(rs.getString("logradouro"));
                    empresa.setNumero(rs.getString("numero"));
                    empresa.setCidade(rs.getString("cidade"));
                    empresa.setEstado(rs.getString("estado"));
                    empresa.setCep(rs.getString("cep"));
                    empresa.setTelefone(rs.getString("telefone"));
                    empresa.setCelular(rs.getString("celular"));
                    empresa.setEmailPrincipal(rs.getString("email_principal"));
                    empresa.setSenha(rs.getString("senha"));
                    empresa.setSite(rs.getString("site"));
                    empresa.setNomeResponsavel(rs.getString("nome_responsavel"));
                    empresa.setCargoResponsavel(rs.getString("cargo_responsavel"));
                    empresa.setEmailResponsavel(rs.getString("email_responsavel"));
                    empresa.setFuncionariosCampo(rs.getInt("funcionarios_campo"));
                    empresa.setCertificacoes(rs.getString("certificacoes"));
                    empresa.setEquipamentos(rs.getString("equipamentos"));
                    empresa.setAreaCobertura(rs.getString("area_cobertura"));
                    empresa.setParcerias(rs.getString("parcerias"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar empresa por email: " + e.getMessage());
            e.printStackTrace();
        }
        return empresa;
    }
    
    
}
