package com.arborismo.model;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;


public class Arvore {
	private int id;
    private int projetoId;
    private String codigoQr;
    private String nomePopular;
    private String nomeCientifico;
    private String numeroPlaqueta;
    private String localizacao;
    private BigDecimal alturaM;
    private BigDecimal dapCm;
    private int idadeEstimada;
    private String inclinacaoTronco;
    private boolean raizesExpostas;
    private String formaCopa;
    private String pragasDoencas;
    private String ocoTronco;
    private String rachadurasFissuras;
    private String riscoGalhos;
    private LocalDate dataUltimaPoda;
    private String tipoUltimaPoda;
    private String historicoQuedas;
    private String proximidadeRisco;
    private String avaliacaoRisco;
    private LocalDate dataInspecao;
    private String responsavelInspecao;
    private String observacoesAdicionais;
    private String situacaoRecomendada;
    private LocalDate proximaInspecao;
    
    // Construtor padrão (vazio)
    public Arvore() {
    	
    }
    
    public Arvore(int id, int projetoId, String codigoQr, String nomePopular, String nomeCientifico, String numeroPlaqueta, String localizacao, BigDecimal alturaM, BigDecimal dapCm, int idadeEstimada, String inclinacaoTronco, boolean raizesExpostas, String formaCopa, String pragasDoencas, String ocoTronco, String rachadurasFissuras, String riscoGalhos, LocalDate dataUltimaPoda, String tipoUltimaPoda, String historicoQuedas, String proximidadeRisco, String avaliacaoRisco, LocalDate dataInspecao, String responsavelInspecao, String observacoesAdicionais, String situacaoRecomendada, LocalDate proximaInspecao) {
    	this.id = id;
        this.projetoId = projetoId;
        this.codigoQr = codigoQr;
        this.nomePopular = nomePopular;
        this.nomeCientifico = nomeCientifico;
        this.numeroPlaqueta = numeroPlaqueta;
        this.localizacao = localizacao;
        this.alturaM = alturaM;
        this.dapCm = dapCm;
        this.idadeEstimada = idadeEstimada;
        this.inclinacaoTronco = inclinacaoTronco;
        this.raizesExpostas = raizesExpostas;
        this.formaCopa = formaCopa;
        this.pragasDoencas = pragasDoencas;
        this.ocoTronco = ocoTronco;
        this.rachadurasFissuras = rachadurasFissuras;
        this.riscoGalhos = riscoGalhos;
        this.dataUltimaPoda = dataUltimaPoda;
        this.tipoUltimaPoda = tipoUltimaPoda;
        this.historicoQuedas = historicoQuedas;
        this.proximidadeRisco = proximidadeRisco;
        this.avaliacaoRisco = avaliacaoRisco;
        this.dataInspecao = dataInspecao;
        this.responsavelInspecao = responsavelInspecao;
        this.observacoesAdicionais = observacoesAdicionais;
        this.situacaoRecomendada = situacaoRecomendada;
        this.proximaInspecao = proximaInspecao;
    	
    }
    
    // --- Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getProjetoId() { return projetoId; }
    public void setProjetoId(int projetoId) { this.projetoId = projetoId; }
    public String getCodigoQr() { return codigoQr; }
    public void setCodigoQr(String codigoQr) { this.codigoQr = codigoQr; }
    public String getNomePopular() { return nomePopular; }
    public void setNomePopular(String nomePopular) { this.nomePopular = nomePopular; }
    public String getNomeCientifico() { return nomeCientifico; }
    public void setNomeCientifico(String nomeCientifico) { this.nomeCientifico = nomeCientifico; }
    public String getNumeroPlaqueta() { return numeroPlaqueta; }
    public void setNumeroPlaqueta(String numeroPlaqueta) { this.numeroPlaqueta = numeroPlaqueta; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public BigDecimal getAlturaM() { return alturaM; }
    public void setAlturaM(BigDecimal alturaM) { this.alturaM = alturaM; }
    public BigDecimal getDapCm() { return dapCm; }
    public void setDapCm(BigDecimal dapCm) { this.dapCm = dapCm; }
    public int getIdadeEstimada() { return idadeEstimada; }
    public void setIdadeEstimada(int idadeEstimada) { this.idadeEstimada = idadeEstimada; }
    public String getInclinacaoTronco() { return inclinacaoTronco; }
    public void setInclinacaoTronco(String inclinacaoTronco) { this.inclinacaoTronco = inclinacaoTronco; }
    public boolean isRaizesExpostas() { return raizesExpostas; }
    public void setRaizesExpostas(boolean raizesExpostas) { this.raizesExpostas = raizesExpostas; }
    public String getFormaCopa() { return formaCopa; }
    public void setFormaCopa(String formaCopa) { this.formaCopa = formaCopa; }
    public String getPragasDoencas() { return pragasDoencas; }
    public void setPragasDoencas(String pragasDoencas) { this.pragasDoencas = pragasDoencas; }
    public String getOcoTronco() { return ocoTronco; }
    public void setOcoTronco(String ocoTronco) { this.ocoTronco = ocoTronco; }
    public String getRachadurasFissuras() { return rachadurasFissuras; }
    public void setRachadurasFissuras(String rachadurasFissuras) { this.rachadurasFissuras = rachadurasFissuras; }
    public String getRiscoGalhos() { return riscoGalhos; }
    public void setRiscoGalhos(String riscoGalhos) { this.riscoGalhos = riscoGalhos; }
    public LocalDate getDataUltimaPoda() { return dataUltimaPoda; }
    public void setDataUltimaPoda(LocalDate dataUltimaPoda) { this.dataUltimaPoda = dataUltimaPoda; }
    public String getTipoUltimaPoda() { return tipoUltimaPoda; }
    public void setTipoUltimaPoda(String tipoUltimaPoda) { this.tipoUltimaPoda = tipoUltimaPoda; }
    public String getHistoricoQuedas() { return historicoQuedas; }
    public void setHistoricoQuedas(String historicoQuedas) { this.historicoQuedas = historicoQuedas; }
    public String getProximidadeRisco() { return proximidadeRisco; }
    public void setProximidadeRisco(String proximidadeRisco) { this.proximidadeRisco = proximidadeRisco; }
    public String getAvaliacaoRisco() { return avaliacaoRisco; }
    public void setAvaliacaoRisco(String avaliacaoRisco) { this.avaliacaoRisco = avaliacaoRisco; }
    public LocalDate getDataInspecao() { return dataInspecao; }
    public void setDataInspecao(LocalDate dataInspecao) { this.dataInspecao = dataInspecao; }
    public String getResponsavelInspecao() { return responsavelInspecao; }
    public void setResponsavelInspecao(String responsavelInspecao) { this.responsavelInspecao = responsavelInspecao; }
    public String getObservacoesAdicionais() { return observacoesAdicionais; }
    public void setObservacoesAdicionais(String observacoesAdicionais) { this.observacoesAdicionais = observacoesAdicionais; }
    public String getSituacaoRecomendada() { return situacaoRecomendada; }
    public void setSituacaoRecomendada(String situacaoRecomendada) { this.situacaoRecomendada = situacaoRecomendada; }
    public LocalDate getProximaInspecao() { return proximaInspecao; }
    public void setProximaInspecao(LocalDate proximaInspecao) { this.proximaInspecao = proximaInspecao; }
    
    
    
    
    
    
    
    
    
    
    

}
