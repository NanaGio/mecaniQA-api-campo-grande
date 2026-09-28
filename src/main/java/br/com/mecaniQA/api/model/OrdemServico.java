package br.com.mecaniQA.api.model;

import br.com.mecaniQA.api.model.enums.StatusOrdemServico;

// US01
public class OrdemServico {
    private Long ordemServicoId;
    private StatusOrdemServico status;

    // Builder
    private OrdemServico(Builder builder) {
        this.ordemServicoId = builder.ordemServicoId;
        this.status = builder.status;
    }

    // Getters e Setters
    public StatusOrdemServico getStatus() {
        return status;
    }
    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }
    public Long getId() { return ordemServicoId; }
    public void setId(Long id) { this.ordemServicoId = id ;}

    // Método estático
    public static Builder builder() {
        return new Builder();
    }

    // Classe estática do Builder
    public static class Builder { //BUILDER
        // Atributos
        private Long ordemServicoId;
        private StatusOrdemServico status;

        // Métodos setter-like
        public Builder id(Long id) { this.ordemServicoId = ordemServicoId; return this; }
        public Builder status(StatusOrdemServico status) { this.status = status; return this;}

        public OrdemServico build(){
            return new OrdemServico(this);
        }
    }
}
