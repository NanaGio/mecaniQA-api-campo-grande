package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.ServicoDTO;
import br.com.mecaniQA.api.model.Servico;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicoMapper {
    public static Servico map(ResultSet resultado) throws SQLException {
        Servico servico = new Servico();
        servico.setIdServico(resultado.getInt("servico_id"));
        servico.setNomeServico(resultado.getString("nome_servico"));
        servico.setTempoEstimado(resultado.getInt("tempo_estimado"));
        servico.setCustoTabelado(resultado.getBigDecimal("custo_tabelado"));
        return servico;
    }
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
