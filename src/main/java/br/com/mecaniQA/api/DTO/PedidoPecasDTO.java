package br.com.mecaniQA.api.DTO;

import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;

import java.util.ArrayList;
import java.util.List;

public class PedidoPecasDTO {
    private Long id;
    private PecaDTO peca;
    private StatusPedidoPecas status;
    private List<ItemPedidoDTO> itens = new ArrayList<>();

    public PedidoPecasDTO() {
    }

    public PedidoPecasDTO(Long id, PecaDTO peca, StatusPedidoPecas status, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.peca = peca;
        this.status = status;
        this.itens = itens != null ? itens : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PecaDTO getPeca() {
        return peca;
    }

    public void setPeca(PecaDTO peca) {
        this.peca = peca;
    }

    public StatusPedidoPecas getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoPecas status) {
        this.status = status;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
