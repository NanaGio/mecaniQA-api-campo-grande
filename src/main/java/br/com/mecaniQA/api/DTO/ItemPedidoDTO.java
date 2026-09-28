package br.com.mecaniQA.api.DTO;

// Na camada DTO, expomos os dados necessários para o item do pedido usando PecaDTO
public class ItemPedidoDTO {
    private Long id;
    private PecaDTO peca;
    private int quantidade;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long id, PecaDTO peca, int quantidade) {
        this.id = id;
        this.peca = peca;
        this.quantidade = quantidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
