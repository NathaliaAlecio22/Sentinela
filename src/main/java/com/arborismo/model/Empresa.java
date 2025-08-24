package com.arborismo.model;

public class Empresa {
	private int id;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoEstadual;
	private String ramoAtividade;
	private String logradouro;
	private String numero;
	private String cidade;
	private String estado;
	private String cep;
	private String telefone;
	private String celular;
	private String emailPrincipal;
	private String senha;
	private String site;
	private String nomeResponsavel;
	private String cargoResponsavel;
	private String emailResponsavel;
	private int funcionariosCampo;
	private String certificacoes;
	private String equipamentos;
	private String areaCobertura;
	private String parcerias;
	
	
	// Construtor padrão (vazio)
	public Empresa() {
		
	}
	
	// Construtor completo (opcional, pode ser útil para testes)
	public Empresa(int id, String razaoSocial, String nomeFantasia, String cnpj, String inscricaoEstadual, String ramoAtividade, String logradouro, String numero, String cidade, String estado, String cep, String telefone, String celular, String emailPrincipal, String senha, String site, String nomeResponsavel, String cargoResponsavel, String emailResponsavel, int funcionariosCampo, String certificacoes, String equipamentos, String areaCobertura, String parcerias) {
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.ramoAtividade = ramoAtividade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.celular = celular;
		this.emailPrincipal = emailPrincipal;
		this.senha = senha;
		this.site = site;
		this.nomeResponsavel = nomeResponsavel;
		this.cargoResponsavel = cargoResponsavel;
		this.emailResponsavel = emailResponsavel;
		this.funcionariosCampo = funcionariosCampo;
		this.certificacoes = certificacoes;
		this.equipamentos = equipamentos;
		this.areaCobertura = areaCobertura;
		this.parcerias = parcerias;
		
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getRazaoSocial() {return razaoSocial;}
	public void setRazaoSocial(String razaoSocial) {this.razaoSocial = razaoSocial;}
	public String getNomeFantasia() { return nomeFantasia; }
	public void setNomeFantasia (String nomeFantasia) {this.nomeFantasia = nomeFantasia; }
	public String getCnpj() {return cnpj;}
	public void setCnpj(String cnpj) {this.cnpj = cnpj;}
	public String getInscricaoEstadual() {return inscricaoEstadual;}
	public void setInscricaoEstadual(String inscricaoEstadual) {this.inscricaoEstadual = inscricaoEstadual;}
	public String getRamoAtividade() {return ramoAtividade;}
	public void setRamoAtividade(String ramoAtividade) {this.ramoAtividade = ramoAtividade;}
	public String getLogradouro() {return logradouro;}
	public void setLogradouro(String logradouro) {this.logradouro = logradouro;}
	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}
	public String getCidade() {return cidade;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getEmailPrincipal() { return emailPrincipal; }
    public void setEmailPrincipal(String emailPrincipal) { this.emailPrincipal = emailPrincipal; }
    public String getSenha() { return senha; } // Getter ajustado
    public void setSenha(String senha) { this.senha = senha; } // Setter ajustado
    public String getSite() { return site; }
    public void setSite(String site) { this.site = site; }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }
    public String getCargoResponsavel() { return cargoResponsavel; }
    public void setCargoResponsavel(String cargoResponsavel) { this.cargoResponsavel = cargoResponsavel; }
    public String getEmailResponsavel() { return emailResponsavel; }
    public void setEmailResponsavel(String emailResponsavel) { this.emailResponsavel = emailResponsavel; }
    public int getFuncionariosCampo() { return funcionariosCampo; }
    public void setFuncionariosCampo(int funcionariosCampo) { this.funcionariosCampo = funcionariosCampo; }
    public String getCertificacoes() { return certificacoes; }
    public void setCertificacoes(String certificacoes) { this.certificacoes = certificacoes; }
    public String getEquipamentos() { return equipamentos; }
    public void setEquipamentos(String equipamentos) { this.equipamentos = equipamentos; }
    public String getAreaCobertura() { return areaCobertura; }
    public void setAreaCobertura(String areaCobertura) { this.areaCobertura = areaCobertura; }
    public String getParcerias() { return parcerias; }
    public void setParcerias(String parcerias) { this.parcerias = parcerias; }
	
	
	
}
