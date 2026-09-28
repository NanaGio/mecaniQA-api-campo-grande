package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.ServicoDTO;
import br.com.mecaniQA.api.model.Servico;

public class ServicoMapper {
    public static Servico toEntity(ServicoDTO dto){
        if (dto == null) return null;

        Servico servico = new Servico();
        servico.setIdServico(dto.getIdServico());
        servico.setNomeServico(dto.getNomeServico());
        servico.setTempoEstimado(dto.getTempoEstimado());
        servico.setCustoTabelado(dto.getCustoTabelado());
        return servico;
    }

    public static ServicoDTO toDTO(Servico entity){
        if (entity == null) return null;

        ServicoDTO dto = new ServicoDTO();
        dto.setIdServico(entity.getIdServico());
        dto.setNomeServico(entity.getNomeServico());
        dto.setTempoEstimado(entity.getTempoEstimado());
        dto.setCustoTabelado(entity.getCustoTabelado());
        return dto;
    }
}
