package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.PecaDTO;
import br.com.mecaniQA.api.model.Peca;

//Feito na mão sem uso no framework para mapear automaticamente.
//INCOMPLETO

public class PecaMapper {
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


}
