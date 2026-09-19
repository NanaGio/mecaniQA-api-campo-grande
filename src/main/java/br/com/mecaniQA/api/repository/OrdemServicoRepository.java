package br.com.mecaniQA.api.repository;

import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;

import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepository {
    // Instância única para projeto Singleton
    private static OrdemServicoRepository INSTANCE;
    // Banco de dados em memória e auto-incremento de ID
    private final List<OrdemServico> bancoEmMemoria = new ArrayList<>();
    private Long criarId = 1L;

    // Construtor privado
    private OrdemServicoRepository() {
    }

    // Ponto de acesso - singleton
    public static synchronized OrdemServicoRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrdemServicoRepository();
        }
        return INSTANCE;
    }

    // [POST] US01 - Criar uma nova Ordem de Serviço (OS)
    public OrdemServico criarServico(OrdemServico os) {
        if (os.getId() == null) {
            os.setId(criarId++);
        }
        bancoEmMemoria.add(os);
        return os;
    }

    // [PUT] US02 - Modificar o status da Ordem de Serviço (Aberto, Em Execução, Executado, etc.)
    public OrdemServico modificarStatus(Long id, StatusOrdemServico novoStatus) {
        // Substituto do (int i = 0; i < size(); i++)
        for (OrdemServico os : this.bancoEmMemoria) {
            if (id.equals(os.getId())) {
                os.setStatus(novoStatus);
                return os;
            }
        }
        return null;
    }
}
