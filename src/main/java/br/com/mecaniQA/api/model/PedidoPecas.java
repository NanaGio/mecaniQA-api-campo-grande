package br.com.mecaniQA.api.model;

import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;

import java.util.ArrayList;
import java.util.List;

public class PedidoPecas {
    private Long id;
    private Peca peca;
    private StatusPedidoPecas status;
    private List<ItemPedido> itens = new ArrayList<>();

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
