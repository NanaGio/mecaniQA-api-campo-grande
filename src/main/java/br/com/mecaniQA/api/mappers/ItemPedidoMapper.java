package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.ItemPedidoDTO;
import br.com.mecaniQA.api.model.ItemPedido;

public class ItemPedidoMapper {
    // DTO -> Model
    public static ItemPedido toEntity(ItemPedidoDTO dto){
        if (dto == null) return null;

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(dto.getId());
        itemPedido.setPeca(PecaMapper.toEntity(dto.getPeca()));
        itemPedido.setQuantidade(dto.getQuantidade());
        return itemPedido;
    }

    // Model -> DTO
    public static ItemPedidoDTO toDTO(ItemPedido entity){
        if (entity == null) return null;

        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(entity.getId());
        dto.setPeca(PecaMapper.toDTO(entity.getPeca()));
        dto.setQuantidade(entity.getQuantidade());
        return dto;
    }
}
