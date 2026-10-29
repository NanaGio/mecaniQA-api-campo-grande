package br.com.mecaniQA.api.repository;

import br.com.mecaniQA.api.mappers.PecaMapper;
import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.PedidoPecas;
import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoPecaRepository implements Repository<PedidoPecas, Long> {
    private static final String PEDIDO_SELECT =
            "SELECT pp.id_pedido_pecas, pp.status_pedido_pecas FROM tb_pedido_pecas pp ";

    private final DataSource dataSource;

    public PedidoPecaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void fill(PreparedStatement ps, PedidoPecas pedido) throws SQLException {
        if (pedido.getStatus() == null) {
            ps.setNull(1, Types.VARCHAR);
        } else {
            ps.setString(1, pedido.getStatus().name());
        }
    }

    @Override
    public PedidoPecas save(PedidoPecas pedido) {
        String sql = "INSERT INTO tb_pedido_pecas (status_pedido_pecas) VALUES (?)";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    fill(ps, pedido);
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (!keys.next()) {
                            throw new SQLException("O banco não retornou o ID do pedido criado.");
                        }
                        pedido.setId(keys.getLong(1));
                    }
                }
                insertItems(conn, pedido.getId(), pedido.getItens());
                conn.commit();
            } catch (SQLException | RuntimeException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pedido de peças.", e);
        }
        return findById(pedido.getId()).orElse(pedido);
    }

    private void insertItems(Connection conn, Long idPedido, List<ItemPedido> itens) throws SQLException {
        if (itens == null || itens.isEmpty()) {
            return;
        }

        String sql = "INSERT INTO tb_idem_pedido (id_pedido_pecas, peca_id, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (ItemPedido item : itens) {
        if (item == null || item.getPeca() == null || item.getPeca().getIdPeca() == null) {
                    throw new IllegalArgumentException("Cada item do pedido deve referenciar uma peça.");
                }
                ps.setLong(1, idPedido);
                ps.setInt(2, item.getPeca().getIdPeca());
                ps.setInt(3, item.getQuantidade());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        item.setId(keys.getLong(1));
                    }
                }
            }
        }
    }

    private Optional<PedidoPecas> readPedido(Connection conn, long id) throws SQLException {
        PedidoPecas pedido;
        try (PreparedStatement ps = conn.prepareStatement(PEDIDO_SELECT + "WHERE pp.id_pedido_pecas = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                pedido = new PedidoPecas();
                pedido.setId(rs.getLong("id_pedido_pecas"));
                pedido.setStatus(toStatus(rs.getString("status_pedido_pecas")));

            }
        }
        pedido.setItens(readItems(conn, id));
        return Optional.of(pedido);
    }

    private List<ItemPedido> readItems(Connection conn, long idPedido) throws SQLException {
        String sql = "SELECT ip.id_item_pedido, ip.quantidade, p.* " +
                "FROM tb_idem_pedido ip JOIN tb_peca p ON p.peca_id = ip.peca_id " +
                "WHERE ip.id_pedido_pecas = ? ORDER BY ip.id_item_pedido";
        List<ItemPedido> itens = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemPedido item = new ItemPedido();
                    item.setId(rs.getLong("id_item_pedido"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPeca(PecaMapper.map(rs));
                    itens.add(item);
                }
            }
        }
        return itens;
    }

    private StatusPedidoPecas toStatus(String status) {
        return status == null ? null : StatusPedidoPecas.valueOf(status);
    }

    @Override
    public Optional<PedidoPecas> findById(Long id) {
        try (Connection conn = dataSource.getConnection()) {
            return readPedido(conn, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido de peças " + id, e);
        }
    }

    @Override
    public List<PedidoPecas> findAll() {
        String sql = "SELECT id_pedido_pecas FROM tb_pedido_pecas ORDER BY id_pedido_pecas";
        List<Long> ids = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getLong("id_pedido_pecas"));
                }
            }

            List<PedidoPecas> pedidos = new ArrayList<>();
            for (Long id : ids) {
                readPedido(conn, id).ifPresent(pedidos::add);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos de peças.", e);
        }
    }

    @Override
    public PedidoPecas update(PedidoPecas pedido) {
        String sql = "UPDATE tb_pedido_pecas SET status_pedido_pecas = ? WHERE id_pedido_pecas = ?";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                int linhasAfetadas;
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    fill(ps, pedido);
                    ps.setLong(2, pedido.getId());
                    linhasAfetadas = ps.executeUpdate();
                }
                if (linhasAfetadas == 0) {
                    conn.rollback();
                    return null;
                }

                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM tb_idem_pedido WHERE id_pedido_pecas = ?")) {
                    ps.setLong(1, pedido.getId());
                    ps.executeUpdate();
                }
                insertItems(conn, pedido.getId(), pedido.getItens());
                conn.commit();
            } catch (SQLException | RuntimeException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pedido de peças " + pedido.getId(), e);
        }
        return findById(pedido.getId()).orElse(pedido);
    }

    public PedidoPecas addItemAoPedido(Long idPedido, ItemPedido item) {
                if (item == null || item.getPeca() == null || item.getPeca().getIdPeca() == null) {
            throw new IllegalArgumentException("O item deve referenciar uma peça.");
        }
        if (!existsById(idPedido)) {
            return null;
        }

        String sql = "INSERT INTO tb_idem_pedido (id_pedido_pecas, peca_id, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, idPedido);
            ps.setInt(2, item.getPeca().getIdPeca());
            ps.setInt(3, item.getQuantidade());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar item ao pedido " + idPedido, e);
        }
        return findById(idPedido).orElse(null);
    }

    public PedidoPecas updateStatus(Long idPedido, StatusPedidoPecas novoStatus) {
        String sql = "UPDATE tb_pedido_pecas SET status_pedido_pecas = ? WHERE id_pedido_pecas = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoStatus.name());
            ps.setLong(2, idPedido);
            if (ps.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do pedido " + idPedido, e);
        }
        return findById(idPedido).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM tb_idem_pedido WHERE id_pedido_pecas = ?")) {
                    ps.setLong(1, id);
                    ps.executeUpdate();
                }
                int linhasAfetadas;
                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM tb_pedido_pecas WHERE id_pedido_pecas = ?")) {
                    ps.setLong(1, id);
                    linhasAfetadas = ps.executeUpdate();
                }
                conn.commit();
                return linhasAfetadas > 0;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pedido de peças " + id, e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT 1 FROM tb_pedido_pecas WHERE id_pedido_pecas = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar pedido de peças " + id, e);
        }
    }
}
