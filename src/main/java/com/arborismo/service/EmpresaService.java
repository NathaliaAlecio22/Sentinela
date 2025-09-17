package com.arborismo.service;

import com.arborismo.dao.EmpresaDAO;
import com.arborismo.model.Empresa;

public class EmpresaService {

    private EmpresaDAO empresaDAO = new EmpresaDAO();

    public boolean cadastrarEmpresa(Empresa empresa) {
        // Lógica de negócio: Verificar se o CNPJ ou e-mail já estão cadastrados
        if (empresaDAO.buscarPorCnpj(empresa.getCnpj()) != null) {
            System.err.println("Erro: CNPJ já cadastrado.");
            return false;
        }
        if (empresaDAO.buscarPorEmail(empresa.getEmailPrincipal()) != null) {
            System.err.println("Erro: E-mail já cadastrado.");
            return false;
        }
        // Se as validações passarem, cadastra a empresa
        empresaDAO.adicionarEmpresa(empresa);
        return true;
    }

    public Empresa login(String email, String senha) {
        // Lógica de negócio: Busca a empresa e verifica a senha
        Empresa empresa = empresaDAO.buscarPorEmail(email);
        if (empresa != null && empresa.getSenha().equals(senha)) {
            return empresa; // Login bem-sucedido
        }
        return null; // Falha no login
    }
}