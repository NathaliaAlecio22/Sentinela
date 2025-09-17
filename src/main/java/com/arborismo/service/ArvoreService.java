package com.arborismo.service;

import com.arborismo.dao.ArvoreDAO;
import com.arborismo.dao.ManutencaoDAO;
import com.arborismo.model.Arvore;
import com.arborismo.model.Manutencao;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ArvoreService {

    private ArvoreDAO arvoreDAO = new ArvoreDAO();
    private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

    public boolean adicionarArvore(Arvore arvore) {
        // Lógica de negócio: O projeto precisa ter um nome e uma empresa associada
        if (arvore.getProjetoId() <= 0) {
            System.err.println("Erro: A árvore deve estar associada a um projeto válido.");
            return false;
        }

        // Lógica de negócio: Gerar um código QR único para a árvore
        arvore.setCodigoQr(UUID.randomUUID().toString());

        arvoreDAO.adicionarArvore(arvore);
        return true;
    }

    public List<Arvore> listarArvoresPorProjeto(int projetoId) {
        if (projetoId <= 0) {
            System.err.println("Erro: ID do projeto inválido.");
            return null;
        }
        // Chama o DAO para listar as árvores
        List<Arvore> arvores = arvoreDAO.listarArvoresPorProjeto(projetoId);

        // Lógica de negócio: Para cada árvore, busca o histórico de manutenções
        for (Arvore arvore : arvores) {
            List<Manutencao> manutencoes = manutencaoDAO.listarManutencoesPorArvore(arvore.getId());
            arvore.setHistoricoManutencoes(manutencoes);
        }

        return arvores;
    }

    public Arvore buscarArvorePorCodigo(String codigoQr) {
        if (codigoQr == null || codigoQr.isEmpty()) {
            System.err.println("Erro: Código QR inválido.");
            return null;
        }
        // Chama o DAO para buscar a árvore
        Arvore arvore = arvoreDAO.buscarArvorePorCodigo(codigoQr);

        if (arvore != null) {
            // Lógica de negócio: Busca o histórico de manutenções da árvore encontrada
            List<Manutencao> manutencoes = manutencaoDAO.listarManutencoesPorArvore(arvore.getId());
            arvore.setHistoricoManutencoes(manutencoes);
        }

        return arvore;
    }
    
    public boolean atualizarArvore(Arvore arvore) {
        if (arvore.getId() <= 0) {
            System.err.println("Erro: ID da árvore inválido para atualização.");
            return false;
        }
        return arvoreDAO.atualizarArvore(arvore);
    }
    
    public boolean removerArvore(int id) {
        if (id <= 0) {
            System.err.println("Erro: ID da árvore inválido para remoção.");
            return false;
        }
        return arvoreDAO.removerArvore(id);
    }
    
    
}