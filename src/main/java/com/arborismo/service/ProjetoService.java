package com.arborismo.service;

import com.arborismo.dao.ProjetoDAO;

import com.arborismo.model.Projeto;

import java.time.LocalDate;
import java.util.List;

public class ProjetoService {
	private ProjetoDAO projetoDAO = new ProjetoDAO();
	
	public boolean adicionarProjeto(Projeto projeto) {
		// Lógica de negócio: O projeto precisa ter um nome e uma empresa associada
		if(projeto.getNomeProjeto() == null || projeto.getNomeProjeto().isEmpty()) {
			System.err.println("Erro: O nome do projeto não pode estar vazio.");
			return false;
		}
		
		if(projeto.getEmpresaId()<= 0) {
			System.err.println("Erro: O projeto deve estar associado a uma empresa válida.");
			return false;
		}
		
		// Adiciona a data de criação no momento do registro
		projeto.setDataCriacao(LocalDate.now());
		
		projetoDAO.adicionarProjeto(projeto);
		return true;
	}
	
	public List<Projeto> listarProjetos(int empresaId){
		// Lógica de negócio: Lista todos os projetos de uma empresa específica
		if(empresaId <= 0) {
			System.err.println("Erro: ID da empresa inválido.");
			return null;
		}
		
		return projetoDAO.listarProjetos(empresaId);
	}

}
