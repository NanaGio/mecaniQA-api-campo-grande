package br.com.mecaniQA.api.model;

// Entidade associativa entre Peça e Pedido
public class ItemPedido {
    private Long idPedido;
    private Peca peca;
    private int quantidade;

    public Long getId() {
        return idPedido;
    }

    public void setId(Long id) {
        this.idPedido = idPedido;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
