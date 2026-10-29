package br.com.mecaniQA.api.service;

import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;
import br.com.mecaniQA.api.repository.OrdemServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {
    private final OrdemServicoRepository repository;

    public OrdemServicoService(OrdemServicoRepository repository) {
        this.repository = repository;
    }

    public List<OrdemServico> GetAllOrdemServico(){
        return repository.findAll();
    }

    public OrdemServico getOrdemServicoById(Long id) {
        validarId(id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Ordem de serviço não encontrada: " + id));
    }

    // [POST] US01 - Criar uma nova Ordem de Serviço (OS)
    public OrdemServico criarOrdem(OrdemServico ordemServico) {
        validarOrdem(ordemServico);
        if (ordemServico.getStatus() == null) {
            ordemServico.setStatus(StatusOrdemServico.ABERTO);
        }
        return repository.save(ordemServico);
    }

    // [PUT] US02 - Modificar o status da Ordem de Serviço (Aberto, Em Execução, Executado, etc.)
    public OrdemServico modificarOrdem (Long id, StatusOrdemServico novoStatus){
        validarId(id);
        if (novoStatus == null) {
            throw new RuntimeException("O novo status da ordem de serviço é obrigatório.");
        }
        OrdemServico ordem = getOrdemServicoById(id);
        validarTransicao(ordem.getStatus(), novoStatus);
        ordem.setStatus(novoStatus);
        return repository.update(ordem);
    }

    //DELETE
    public boolean deletarOrdem(Long id) {
        validarId(id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ordem de serviço não encontrada: " + id);
        }
        return repository.deleteById(id);
    }

    // -- VALIDAÇÕES CUSTOMIZADAS --

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("O ID da ordem de serviço deve ser maior que zero.");
        }
    }

    private void validarOrdem(OrdemServico ordem) {
        if (ordem == null) {
            throw new RuntimeException("Os dados da ordem de serviço são obrigatórios.");
        }
        // Regra assumida: uma OS nova sempre começa como ABERTO
        if (ordem.getStatus() != null && ordem.getStatus() != StatusOrdemServico.ABERTO) {
            throw new RuntimeException("Uma nova ordem de serviço deve iniciar com status ABERTO.");
        }
    }

    private void validarTransicao(StatusOrdemServico atual, StatusOrdemServico novo) {
        // Regra assumida: EXECUTADO é um estado final
        if (atual == StatusOrdemServico.EXECUTADO) {
            throw new RuntimeException(
                    "Ordem de serviço já executada: o status não pode mais ser alterado.");
        }
    }
}
