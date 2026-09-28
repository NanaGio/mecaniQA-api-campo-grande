package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.PecaDTO;
import br.com.mecaniQA.api.model.Peca;

public class PecaMapper {
    // DTO -> Model
    public static Peca toEntity(PecaDTO dto){
        if (dto == null) return null;

        Peca peca = new Peca();
        peca.setIdPeca(dto.getIdPeca());
        peca.setCodigoDeBarras(dto.getCodigoDeBarras());
        peca.setFornecedor(dto.getFornecedor());
        peca.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        peca.setTamanho(dto.getTamanho());
        peca.setCor(dto.getCor());
        peca.setCategoriaPeca(dto.getCategoriaPeca());
        peca.setPrecoCusto(dto.getPrecoCusto());
        peca.setPrecoVenda(dto.getPrecoVenda());
        return peca;
    }

    // Model -> DTO
    public static PecaDTO toDTO(Peca entity){
        if (entity == null) return null;

        PecaDTO dto = new PecaDTO();
        dto.setIdPeca(entity.getIdPeca());
        dto.setCodigoDeBarras(entity.getCodigoDeBarras());
        dto.setFornecedor(entity.getFornecedor());
        dto.setQuantidadeEstoque(entity.getQuantidadeEstoque());
        dto.setTamanho(entity.getTamanho());
        dto.setCor(entity.getCor());
        dto.setCategoriaPeca(entity.getCategoriaPeca());
        dto.setPrecoCusto(entity.getPrecoCusto());
        dto.setPrecoVenda(entity.getPrecoVenda());
        return dto;
    }
}
