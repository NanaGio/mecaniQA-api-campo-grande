package br.com.mecaniQA.api.mappers;

import br.com.mecaniQA.api.DTO.PecaDTO;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.model.enums.CategoriaPeca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PecaMapper {
    public static Peca map(ResultSet resultado) throws SQLException {
        Peca peca = new Peca();
        peca.setIdPeca(resultado.getInt("peca_id"));
        peca.setCodigoDeBarras(resultado.getString("cod_barras"));
        peca.setFornecedor(resultado.getString("fornecedor"));
        peca.setQuantidadeEstoque(resultado.getInt("quant_estoque"));
        peca.setPrecoCusto(resultado.getBigDecimal("preco_custo"));
        peca.setPrecoVenda(resultado.getBigDecimal("preco_venda"));
        peca.setDataCadastro(resultado.getObject("data_cadastro", LocalDateTime.class));
        peca.setTamanho(resultado.getObject("tamanho", Float.class));
        peca.setCor(resultado.getString("cor"));
        peca.setCategoriaPeca(CategoriaPeca.valueOf(resultado.getString("categoria_peca")));
        peca.setDataUltimaAtualizacao(resultado.getObject("data_ultima_atualizacao", LocalDateTime.class));
        return peca;
    }
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
