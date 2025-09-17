package com.arborismo.service;

import com.arborismo.dao.RecuperacaoSenhaDAO;
import com.arborismo.model.RecuperacaoSenha;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecuperacaoSenhaService {

    private RecuperacaoSenhaDAO recuperacaoSenhaDAO = new RecuperacaoSenhaDAO();

    public String gerarToken(int empresaId) {
        String token = UUID.randomUUID().toString();
        LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(1); // Token válido por 1 hora
        RecuperacaoSenha recuperacao = new RecuperacaoSenha(0, empresaId, token, dataExpiracao);
        recuperacaoSenhaDAO.criarToken(recuperacao);
        return token;
    }

    public RecuperacaoSenha validarToken(String token) {
        return recuperacaoSenhaDAO.buscarToken(token);
    }
}