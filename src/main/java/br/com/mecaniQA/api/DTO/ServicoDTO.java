package br.com.mecaniQA.api.DTO;

import java.math.BigDecimal;

public class ServicoDTO {
    private Integer idServico;
    private String nomeServico;
    private Integer tempoEstimado;
    private BigDecimal custoTabelado;

    public ServicoDTO(Integer idServico, String nomeServico, Integer tempoEstimado, BigDecimal custoTabelado){
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.tempoEstimado = tempoEstimado;
        this.custoTabelado = custoTabelado;
    }

    //Construtor criado por que o mapper pediu
    public ServicoDTO() {

    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public BigDecimal getCustoTabelado() {
        return custoTabelado;
    }

    public void setCustoTabelado(BigDecimal custoTabelado) {
        this.custoTabelado = custoTabelado;
    }
}
