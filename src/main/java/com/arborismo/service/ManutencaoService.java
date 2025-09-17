package com.arborismo.service;

import com.arborismo.dao.ManutencaoDAO;
import com.arborismo.model.Manutencao;

import java.time.LocalDate;
import java.util.List;

public class ManutencaoService {

    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    public boolean adicionarManutencao(Manutencao manutencao) {
        if (manutencao.getArvoreId() <= 0 || manutencao.getTipo() == null || manutencao.getTipo().isEmpty()) {
            System.err.println("Erro: Dados de manutenção inválidos. Árvore e tipo são obrigatórios.");
            return false;
        }
        manutencao.setDataEvento(LocalDate.now());
        manutencaoDAO.adicionarManutencao(manutencao);
        return true;
    }

    public List<Manutencao> listarManutencoesPorArvore(int arvoreId) {
        if (arvoreId <= 0) {
            System.err.println("Erro: ID da árvore inválido.");
            return null;
        }
        return manutencaoDAO.listarManutencoesPorArvore(arvoreId);
    }
}