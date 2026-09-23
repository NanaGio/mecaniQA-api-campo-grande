package br.com.mecaniQA.api.mappers;

//Lista aqui também?

import br.com.mecaniQA.api.DTO.OrdemServicoDTO;
import br.com.mecaniQA.api.model.OrdemServico;

public class OrdemServicoMapper {
    public static OrdemServico toEntity(OrdemServicoDTO dto){
        // Proteção contra NullPointer
        if (dto == null) return null;
        return OrdemServico.builder()
                 .id(dto.getId())
                 .status(dto.getStatus())
                 .build();
    }

    public static OrdemServicoDTO toDTO(OrdemServico entity) {
        // Proteção contra NullPointer
        if (entity == null) return null;

        return new OrdemServicoDTO(
                entity.getId(),
                entity.getStatus()
        );
    }
}
