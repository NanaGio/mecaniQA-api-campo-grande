package br.com.mecaniQA.api.DTO;

import br.com.mecaniQA.api.model.enums.StatusOrdemServico;

public class OrdemServicoDTO {
    private Long id;
    private StatusOrdemServico status;

    public OrdemServicoDTO(Long id, StatusOrdemServico status){
        this.id = id;
        this.status = status;
    }

    public OrdemServicoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }
}
