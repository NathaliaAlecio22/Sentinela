package com.arborismo.model;

import java.time.LocalDateTime;


public class RecuperacaoSenha {
	private int id;
	private int empresaId;
	private String token;
	private LocalDateTime dataExpiracao;
	
	// Construtor padrão (vazio)
	public RecuperacaoSenha() {
		
	}
	
	// Construtor completo
	public RecuperacaoSenha(int id, int empresaId, String token, LocalDateTime dataExpiracao) {
		this.id = id;
		this.empresaId = empresaId;
		this.token = token;
		this.dataExpiracao = dataExpiracao;
	}
	// --- Getters e Setters
	public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEmpresaId() { return empresaId; }
    public void setEmpresaId(int empresaId) { this.empresaId = empresaId; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public LocalDateTime getDataExpiracao() { return dataExpiracao; }
    public void setDataExpiracao(LocalDateTime dataExpiracao) { this.dataExpiracao = dataExpiracao; }

}
