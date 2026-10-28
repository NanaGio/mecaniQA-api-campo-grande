package br.com.mecaniQA.api.repository;
import br.com.mecaniQA.api.mappers.PecaMapper;
import br.com.mecaniQA.api.model.Peca;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

@Component
public class PecaRepository implements Repository<Peca, Integer>{
    private final DataSource dataSource;


    public PecaRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private void fill(PreparedStatement ps, Peca p) throws  SQLException{
        ps.setString(1, p.getCodigoDeBarras());
        ps.setString(2, p.getFornecedor());
        ps.setInt(3, p.getQuantidadeEstoque());
        ps.setBigDecimal(4, p.getPrecoCusto());
        ps.setBigDecimal(5, p.getPrecoVenda());
        ps.setObject(6, p.getTamanho(), Types.FLOAT);
        ps.setString(7, p.getCor());
        ps.setString(8, p.getCategoriaPeca().name());
    }

    @Override
    public Peca save(Peca entity) {
        String sql = "INSERT INTO tb_peca (cod_barras, fornecedor, quant_estoque, preco_custo, preco_venda, tamanho, cor, categoria_peca) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            fill(ps, entity);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()){
                if (keys.next()) entity.setIdPeca(keys.getInt(1));
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao salvar.", e);
        }
        return findById(entity.getIdPeca()).orElse(entity);
    }

    @Override
    public Optional<Peca> findById(Integer id) {
        String sql = "SELECT * FROM tb_peca WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(PecaMapper.map(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar peça " + id, e);
        }
    }


    @Override
    public List<Peca> findAll() {
        String sql = "SELECT * FROM tb_peca";
        List<Peca> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(PecaMapper.map(rs));
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar peças.", e);
        }
    }

    @Override
    public Peca update(Peca entity) {
        String sql = "UPDATE tb_peca SET cod_barras = ?, fornecedor = ?, quant_estoque = ?, preco_custo = ?, preco_venda = ?, tamanho = ?, cor = ?, categoria_peca = ? WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            fill(ps, entity);
            ps.setInt(9, entity.getIdPeca());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar peça.", e);
        }
        return findById(entity.getIdPeca()).orElse(entity);
    }

    public void updatePrecoCusto(Integer idPeca, BigDecimal precoCusto) {
        String sql = "UPDATE tb_peca SET preco_custo = ? WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, precoCusto);
            ps.setInt(2, idPeca);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar preço de custo da peça " + idPeca, e);
        }
    }

    public void updatePrecoVenda(Integer idPeca, BigDecimal precoVenda) {
        String sql = "UPDATE tb_peca SET preco_venda = ? WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, precoVenda);
            ps.setInt(2, idPeca);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar preço de venda da peça " + idPeca, e);
        }
    }

    public void updateQuantidade(Integer idPeca, Integer quantidade) {
        String sql = "UPDATE tb_peca SET quant_estoque = ? WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantidade);
            ps.setInt(2, idPeca);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar quantidade em estoque da peça " + idPeca, e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM tb_peca WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar peça " + id, e);
        }
    }


    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM tb_peca WHERE peca_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar peça " + id, e);
        }
    }
}
