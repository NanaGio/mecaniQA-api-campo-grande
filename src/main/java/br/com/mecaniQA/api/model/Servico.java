package br.com.mecaniQA.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Servico {
    private Integer idServico;
    private String nomeServico;
    private Integer tempoEstimado;
    private BigDecimal custoTabelado;
    private LocalDateTime dataCriacao;

    // GET & SET [ idServico ]
    public Integer getIdServico() {
        return idServico;
    }
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    // GET & SET [ nomeServico ]
    public String getNomeServico() {
        return nomeServico;
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    // GET & SET [ tempoEstimado ]
    public Integer getTempoEstimado() {
        return tempoEstimado;
    }
    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    // GET & SET [ custoTabelado ]
    public BigDecimal getCustoTabelado() {
        return custoTabelado;
    }
    public void setCustoTabelado(BigDecimal custoTabelado) {
        this.custoTabelado = custoTabelado;
    }

    // GET & SET [ dataCriacao ]
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}