package br.com.mecaniQA.api.mappers;

//Precisa de lista aqui
//CONTINUAR

import br.com.mecaniQA.api.DTO.ItemPedidoDTO;
import br.com.mecaniQA.api.DTO.PedidoPecasDTO;
import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.PedidoPecas;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoPecasMapper {
    public static PedidoPecas toEntity(PedidoPecasDTO dto){
        PedidoPecas pedido = new PedidoPecas();
        pedido.setId(dto.getId());
        pedido.setPeca(PecaMapper.toEntity(dto.getPeca()));
        pedido.setStatus(dto.getStatus());

        List<ItemPedido> itens = dto.getItens().stream()
                .map(ItemPedidoMapper::toEntity)
                .collect(Collectors.toList());
        pedido.setItens(itens);
        return pedido;
    }
}
