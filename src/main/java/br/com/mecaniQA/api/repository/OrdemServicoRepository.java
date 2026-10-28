package br.com.mecaniQA.api.repository;

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
    public Optional<OrdemServico> findById(Long aLong) {
        return Optional.empty();
    }

    //GET ALL
    @Override
    public List<OrdemServico> findAll() {
        return List.of();
    }

    // [PUT] US02 - Modificar o status da Ordem de Serviço (Aberto, Em Execução, Executado, etc.)
    @Override
    public OrdemServico update(OrdemServico entity) {
        return null;
    }

    // [DELETE] Remover uma Ordem de Serviço
    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
