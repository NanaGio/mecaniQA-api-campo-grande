package br.com.mecaniQA.api.repository;

import br.com.mecaniQA.api.mappers.OrdemServicoMapper;
import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.fill;

@Component
public class OrdemServicoRepository implements Repository<OrdemServico, Long> {
    // Substituindo banco em memória
    private final DataSource dataSource;

    public OrdemServicoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // [POST] US01 - Criar uma nova Ordem de Serviço (OS)
    @Override
    public OrdemServico save(OrdemServico entity) {
        String sql = "INSERT INTO tb_ordem_servico (status_ordem_servico) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getStatus().name());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("O banco não retornou o ID da ordem de serviço criada.");
                }
                entity.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar ordem de serviço.", e);
        }
        return findById(entity.getId()).orElse(entity);
    }

    //GET BY ID
    @Override
    public Optional<OrdemServico> findById(Long id) {
        String sql = "SELECT * FROM tb_ordem_servico WHERE id_ordem_servico = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(OrdemServicoMapper.map(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ordem de serviço " + id, e);
        }
    }

    //GET ALL
    @Override
    public List<OrdemServico> findAll() {
        String sql = "SELECT * FROM tb_ordem_servico";
        List<OrdemServico> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(OrdemServicoMapper.map(rs));
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ordens de serviço.", e);
        }
    }

    // [PUT] US02 - Modificar o status da Ordem de Serviço
    @Override
    public OrdemServico update(OrdemServico entity) {
        String sql = "UPDATE tb_ordem_servico SET status_ordem_servico = ? WHERE id_ordem_servico = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getStatus().name());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ordem de serviço.", e);
        }
        return findById(entity.getId()).orElse(entity);
    }

    // [DELETE] Remover uma Ordem de Serviço
    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM tb_ordem_servico WHERE id_ordem_servico = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar ordem de serviço " + id, e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM tb_ordem_servico WHERE id_ordem_servico = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar pedido de peças " + id, e);
        }
    }
}
