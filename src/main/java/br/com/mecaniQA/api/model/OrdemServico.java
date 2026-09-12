package br.com.mecaniQA.api.model;

import br.com.mecaniQA.api.model.enums.StatusOrdemServico;

public class OrdemServico {
    private Long id;
    private StatusOrdemServico status;

    private static OrdemServico() {
        novaOrdem =
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {
        private long id;
        private StatusOrdemServico status;

        public Builder(long id) {
            this.id = id;
        }

        public Builder(StatusOrdemServico status) {
            this.status = status;
        }
    }
}
