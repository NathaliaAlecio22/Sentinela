package com.arborismo.model;
import java.time.LocalDate;


public class Projeto {
	private int id;
	private int empresaId;
	private String nomeProjeto;
	private String descricaoProjeto;
	private LocalDate dataCriacao;
	
	
	// Construtor padrão (vazio)
	public Projeto() {
		
	}
	
	// Construtor completo
	public Projeto(int id, int empresaId, String nomeProjeto, String descricaoProjeto, LocalDate dataCriacao) {
		this.id = id;
		this.empresaId = empresaId;
		this.nomeProjeto = nomeProjeto;
		this.descricaoProjeto = descricaoProjeto;
		this.dataCriacao = dataCriacao;
	}
	
	// --- Getters e Setters
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getEmpresaId() {return empresaId;}
	public void setEmpresaId(int empresaId) {this.empresaId = empresaId;}
	public String getNomeProjeto() {return nomeProjeto;}
	public void setNomeProjeto(String nomeProjeto) {this.nomeProjeto = nomeProjeto;}
	public String getDescricaoProjeto() {return descricaoProjeto;}
	public void setDescricaoProjeto(String descricaoProjeto) {this.descricaoProjeto = descricaoProjeto;}
	public LocalDate getDataCriacao() {return dataCriacao;}
	public void setDataCriacao(LocalDate dataCriacao) {this.dataCriacao = dataCriacao;}
}
