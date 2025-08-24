package com.arborismo.model;

import java.time.LocalDate;

public class Manutencao {
	private int id;
	private int arvoreId;
	private String tipo;
	private LocalDate dataEvento;
	private String descricao;

	
	// Construtor padrão (vazio)
	public Manutencao() {
		
	}
	
	// Construtor completo
	public Manutencao(int id, int arvoreId, String tipo, LocalDate dataEvento, String descricao) {
		this.id = id;
		this.arvoreId = arvoreId;
		this.tipo = tipo;
		this.dataEvento = dataEvento;
		this.descricao = descricao;
	}
	
	// --- Getters e Setters
	public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getArvoreId() { return arvoreId; }
    public void setArvoreId(int arvoreId) { this.arvoreId = arvoreId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
