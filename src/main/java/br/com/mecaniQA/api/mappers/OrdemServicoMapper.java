package br.com.mecaniQA.api.mappers;

//Lista aqui também?

import br.com.mecaniQA.api.DTO.OrdemServicoDTO;
import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemServicoMapper {
    public static OrdemServico map(ResultSet rs) throws SQLException {
        String status = rs.getString("status_ordem_servico");

        OrdemServico ordem = OrdemServico.builder()
                .status(status == null ? null : StatusOrdemServico.valueOf(status))
                .build();

        ordem.setId(rs.getLong("id_ordem_servico"));
        return ordem;
    }

    public static OrdemServico toEntity(OrdemServicoDTO dto){

        if (dto == null) return null;
        return OrdemServico.builder()
                 .id(dto.getId())
                 .status(dto.getStatus())
                 .build();
    }

    public static OrdemServicoDTO toDTO(OrdemServico entity) {

        if (entity == null) return null;

        return new OrdemServicoDTO(
                entity.getId(),
                entity.getStatus()
        );
    }
}
