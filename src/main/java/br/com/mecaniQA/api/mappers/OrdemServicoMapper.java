package br.com.mecaniQA.api.mappers;

//Lista aqui também?

import br.com.mecaniQA.api.DTO.OrdemServicoDTO;
import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.OrdemServico;

import java.util.List;

public class OrdemServicoMapper {
    public static OrdemServico toEntity(OrdemServicoDTO dto){
        OrdemServico ordem = new OrdemServico();
        ordem.setId(dto.getId());
        ordem.setStatus(dto.getStatus());
        return ordem;
    }

    public static OrdemServicoDTO toDTO(OrdemServico entity){
        OrdemServicoDTO dto = new OrdemServicoDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
