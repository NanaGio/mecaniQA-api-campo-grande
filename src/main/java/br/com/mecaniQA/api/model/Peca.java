package br.com.mecaniQA.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
}