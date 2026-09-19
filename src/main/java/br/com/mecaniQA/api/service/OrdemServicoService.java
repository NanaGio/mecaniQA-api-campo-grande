package br.com.mecaniQA.api.service;

import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;
import br.com.mecaniQA.api.repository.OrdemServicoRepository;

public class OrdemServicoService {
    private static OrdemServicoService INSTANCE;
    private final OrdemServicoRepository repository = OrdemServicoRepository.getInstance();

    // Construtor privado
    private OrdemServicoService() {}

    // Criando nova instância
    public static OrdemServicoService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrdemServicoService();
        }
        return INSTANCE;
    }

    // [POST] US01 - Criar uma nova Ordem de Serviço (OS)
    public OrdemServico criarOrdem(OrdemServico ordemServico) {
        if (ordemServico.getStatus() == null) {
            ordemServico.setStatus(StatusOrdemServico.ABERTO);
        }
        return repository.criarServico(ordemServico);
    }

    // [PUT] US02 - Modificar o status da Ordem de Serviço (Aberto, Em Execução, Executado, etc.)
    public OrdemServico modificarOrdem (Long id, StatusOrdemServico novoStatus){
        return repository.modificarStatus(id, novoStatus);
    }
}
