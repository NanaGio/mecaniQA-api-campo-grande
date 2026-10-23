package br.com.mecaniQA.api.repository;

//Fontes de base: medium.com/@adityashete009/implementing-singleton-classes-in-java | devmedia.com.br/padrao-de-projeto-singleton-em-java | Singleton em Java (Lucas Herom) / Youtube

//Acessar sempre via getInstance(), nunca new, para não quebrar a regra do singleton.

import br.com.mecaniQA.api.mappers.PecaMapper;
import br.com.mecaniQA.api.mappers.ServicoMapper;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.model.Servico;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ServicoRepository implements  Repository<Servico, Integer>{

    private final DataSource dataSource;

    public ServicoRepository (DataSource dataSource){
        this.dataSource = dataSource;
    }

    private void fill(PreparedStatement ps, Servico s) throws SQLException {
        ps.setString(1, s.getNomeServico());
        ps.setInt(2, s.getTempoEstimado());
        ps.setBigDecimal(3, s.getCustoTabelado());
    }


    @Override
    public Servico save(Servico entity) {
        String sql = "INSERT INTO tb_servico (nome_servico, tempo_estimado, custo_tabelado) VALUES (?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            fill(ps, entity);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) entity.setIdServico(keys.getInt(1));
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao salvar", e);
        }
        return findById(entity.getIdServico()).orElse(entity);
    }

    @Override
    public Optional<Servico> findById(Integer id) {
        String sql = "SELECT * FROM tb_servico WHERE servico_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next() ? Optional.of(ServicoMapper.map(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("ID não encontrado", e);
        }
    }

    @Override
    public List<Servico> findAll() {
        String sql = "SELECT * FROM tb_servico";
        List<Servico> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()) lista.add(ServicoMapper.map(rs));
            return lista;
        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar serviços.", e);
        }
    }

    @Override
    public Servico update(Servico entity) {
        String sql = "UPDATE tb_servico SET tempo_estimado = ?, custo_tabelado = ? WHERE servico_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, entity.getTempoEstimado());
            ps.setBigDecimal(2, entity.getCustoTabelado());
            ps.setInt(3, entity.getIdServico());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar", e);
        }
        return findById(entity.getIdServico()).orElse(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM tb_servico WHERE servico_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e){
            throw new RuntimeException("Erro ao deletar serviço", e);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT 1 FROM tb_servico WHERE servico_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e){
            throw new RuntimeException("Serviço não econtrado", e);
        }
    }
    // Criando instância
}
    //Outros métodos, get ou setters...

