package br.com.mecaniQA.api.DTO;

import br.com.mecaniQA.api.model.Peca;

//Na camada DTO, escolhemos apenas atributos que queremos expor, então segui a lógica de selecionar apenas os atributos para expor ao controller.
public class ItemPedidoDTO {
    private Long id;
    private Peca peca;
    private int quantidade;

    public ItemPedidoDTO(Long id, Peca peca, int quantidade){
        this.id = id;
        this.peca = peca;
        this.quantidade = quantidade;
    }

    public ItemPedidoDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
