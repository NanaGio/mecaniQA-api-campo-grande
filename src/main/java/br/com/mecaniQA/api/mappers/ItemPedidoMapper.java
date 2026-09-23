package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.ItemPedidoDTO;
import br.com.mecaniQA.api.model.ItemPedido;

public class ItemPedidoMapper {
    public static ItemPedido toEntity(ItemPedidoDTO dto){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(dto.getId());
        itemPedido.setQuantidade(dto.getQuantidade());
        return itemPedido;
    }

    public static ItemPedidoDTO toDTO(ItemPedido entity){
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(entity.getId());
        dto.setQuantidade(entity.getQuantidade());
        return dto;
    }
}
