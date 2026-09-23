package br.com.mecaniQA.api.DTO;

import br.com.mecaniQA.api.model.enums.CategoriaPeca;

import java.math.BigDecimal;

public class PecaDTO {
    private Integer idPeca;
    private String codigoDeBarras;
    private String fornecedor;
    private Float tamanho;
    private String cor;
    private CategoriaPeca categoriaPeca;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;

    public PecaDTO(Integer idPeca, String codigoDeBarras, String fornecedor, Float tamanho, String cor, CategoriaPeca categoriaPeca, BigDecimal precoCusto, BigDecimal precoVenda){
        this.idPeca = idPeca;
        this.codigoDeBarras = codigoDeBarras;
        this.fornecedor = fornecedor;
        this.tamanho = tamanho;
        this.cor = cor;
        this.categoriaPeca = categoriaPeca;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    //Construtor criado por que o mapper pediu
    public PecaDTO() {

    }

    public Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Float getTamanho() {
        return tamanho;
    }

    public void setTamanho(Float tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public CategoriaPeca getCategoriaPeca() {
        return categoriaPeca;
    }

    public void setCategoriaPeca(CategoriaPeca categoriaPeca) {
        this.categoriaPeca = categoriaPeca;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }
}
