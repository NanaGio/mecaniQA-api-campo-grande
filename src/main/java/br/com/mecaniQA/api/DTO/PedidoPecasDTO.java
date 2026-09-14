package br.com.mecaniQA.api.DTO;

import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;

import java.util.ArrayList;
import java.util.List;

public class PedidoPecasDTO {
    private Long id;
    private Peca peca;
    private StatusPedidoPecas status;
    private List<ItemPedido> itens = new ArrayList<>();

    public PedidoPecasDTO(Long id, Peca peca, StatusPedidoPecas status, List<ItemPedido> itens){
        this.id = id;
        this.peca = peca;
        this.status = status;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public StatusPedidoPecas getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoPecas status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}
