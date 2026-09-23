package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.PecaDTO;
import br.com.mecaniQA.api.model.Peca;

//Feito na mão sem uso no framework para mapear automaticamente.
//INCOMPLETO

public class PecaMapper {
    //Pelo o que eu entendi, aqui nos vamos ter a lógica da entrada do usuário (toEntity)
    public static Peca toEntity(PecaDTO dto){
        Peca peca = new Peca();
        peca.setIdPeca(dto.getIdPeca());
        peca.setCodigoDeBarras(dto.getCodigoDeBarras());
        peca.setFornecedor(dto.getFornecedor());
        peca.setTamanho(dto.getTamanho());
        peca.setCor(dto.getCor());
        peca.setCategoriaPeca(dto.getCategoriaPeca());
        peca.setPrecoCusto(dto.getPrecoCusto());
        peca.setPrecoVenda(dto.getPrecoVenda());
        return peca;
    }

    public static PecaDTO toDTO(Peca entity){
        PecaDTO dto = new PecaDTO();
        dto.setIdPeca(entity.getIdPeca());
        dto.setCodigoDeBarras(entity.getCodigoDeBarras());
        dto.setFornecedor(entity.getFornecedor());
        dto.setTamanho(entity.getTamanho());
        dto.setCor(entity.getCor());
        dto.setCategoriaPeca(entity.getCategoriaPeca());
        dto.setPrecoCusto(entity.getPrecoCusto());
        dto.setPrecoVenda(entity.getPrecoVenda());
        return dto;
    }

    //E aqui seria para "fora", para a API

}
