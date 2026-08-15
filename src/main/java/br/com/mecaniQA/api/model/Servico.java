package br.com.mecaniQA.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Servico {
    private Integer idServico;
    private String nomeServico;
    private Integer tempoEstimado;
    private BigDecimal custoTabelado;
    private LocalDateTime dataCriacao;
}