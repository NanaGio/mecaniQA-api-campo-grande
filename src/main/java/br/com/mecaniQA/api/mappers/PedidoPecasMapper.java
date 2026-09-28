package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.ItemPedidoDTO;
import br.com.mecaniQA.api.DTO.PedidoPecasDTO;
import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.PedidoPecas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoPecasMapper {

    // DTO -> Model
    public static PedidoPecas toEntity(PedidoPecasDTO dto) {
        if (dto == null) return null;

        PedidoPecas pedido = new PedidoPecas();
        pedido.setId(dto.getId());
        pedido.setPeca(PecaMapper.toEntity(dto.getPeca()));
        pedido.setStatus(dto.getStatus());

        if (dto.getItens() != null) {
            List<ItemPedido> itens = dto.getItens().stream()
                    .map(ItemPedidoMapper::toEntity)
                    .collect(Collectors.toList());
            pedido.setItens(itens);
        } else {
            pedido.setItens(new ArrayList<>());
        }

        return pedido;
    }

    // Model -> DTO
    public static PedidoPecasDTO toDTO(PedidoPecas entity) {
        if (entity == null) return null;

        PedidoPecasDTO dto = new PedidoPecasDTO();
        dto.setId(entity.getId());
        dto.setPeca(PecaMapper.toDTO(entity.getPeca()));
        dto.setStatus(entity.getStatus());

        if (entity.getItens() != null) {
            List<ItemPedidoDTO> itensDto = entity.getItens().stream()
                    .map(ItemPedidoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setItens(itensDto);
        } else {
            dto.setItens(new ArrayList<>());
        }

        return dto;
    }
}
