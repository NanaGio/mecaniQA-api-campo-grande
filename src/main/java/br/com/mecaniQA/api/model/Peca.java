package br.com.mecaniQA.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// TERMINAR MÉTODOS!
public class Peca {
    private Integer idPeca;
    private String codigoDeBarras;
    private String fornecedor;
    private Integer quantidadeEstoque;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private LocalDateTime dataCadastro;
    private Float tamanho;
    private String cor;
    private CategoriaPeca categoriaPeca;

    // GET & SET [ idPeca ]
    public Integer getIdPeca() {
        return idPeca;
    }
    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

    //GET & SET [ codigoDeBarras ]
    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }
    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    //GET & SET [ fornecedor ]
    public String getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    //GET & SET [ quantidadeEstoque ]
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    //GET & SET [ precoCusto ]
    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }
    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    //GET & SET [ precoVenda ]
    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    //GET & SET [ dataCadastro ]
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) { // <- dataAtualizacaoPeca
        this.dataCadastro = dataCadastro;
    }

    //GET & SET [ tamanho ]
    public Float getTamanho() {
        return tamanho;
    }
    public void setTamanho(Float tamanho) {
        this.tamanho = tamanho;
    }

    //GET & SET [ cor ]
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    //GET & SET [ categoriaPeca ]
    public CategoriaPeca getCategoriaPeca() {
        return categoriaPeca;
    }
    public void setCategoriaPeca(CategoriaPeca categoriaPeca) {
        this.categoriaPeca = categoriaPeca;
    }
}