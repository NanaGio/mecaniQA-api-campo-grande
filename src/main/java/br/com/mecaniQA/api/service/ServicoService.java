package br.com.mecaniQA.api.service;

import br.com.mecaniQA.api.model.Servico;
import br.com.mecaniQA.api.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServicoService {
   private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }


    //GET ALL
    public List<Servico> GetAllServicos(){
        return repository.findAll();
    }
    //GET BY ID
    public Servico getServicoById(Integer idServico){
        return repository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado."));
    }
    //POST
    public Servico salvarServico(Servico servico){
        validarServico(servico);
        return repository.save(servico);
    }
    //PUT - tempoEstimado
    public Servico updateTempoEstimado(Integer idServico, Integer novoTempoEstimado){
        validarTempoEstimado(novoTempoEstimado);
        Servico servico = getServicoById(idServico);
        servico.setTempoEstimado(novoTempoEstimado);
        validarServico(servico);
        return repository.update(servico);
    }
    //PUT - custoTabelado
    public Servico updateCustoTabelado(Integer idServico, BigDecimal novoCustoTabelado){
        validarCustoTabelado(novoCustoTabelado);
        Servico servico = getServicoById(idServico);
        servico.setCustoTabelado(novoCustoTabelado);
        validarServico(servico);
        return repository.update(servico);
    }
    //DELETE
    public boolean deletarServico(Integer idServico){
        if (!repository.existsById(idServico)){
            throw new RuntimeException("Serviço não econtrado.");
        }
        return repository.deleteById(idServico);
    }

    // -- VALIDAÇÕES CUSTOMIZADAS --
    private void validarServico(Servico servico) {
        if (servico == null) {
            throw new RuntimeException("Dados do serviço não informados.");
        }
        if (servico.getNomeServico() == null || servico.getNomeServico().isBlank()
                || servico.getNomeServico().length() > 30) {
            throw new RuntimeException("Nome do serviço obrigatório, com até 30 caracteres.");
        }
        validarTempoEstimado(servico.getTempoEstimado());
        validarCustoTabelado(servico.getCustoTabelado());
    }

    private void validarTempoEstimado(Integer tempoEstimado) {
        if (tempoEstimado == null || tempoEstimado <= 0) {
            throw new RuntimeException("O tempo estimado deve ser maior que zero.");
        }
    }

    private void validarCustoTabelado(BigDecimal custoTabelado) {
        if (custoTabelado == null || custoTabelado.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("O custo tabelado deve ser maior ou igual a zero.");
        }
    }
}
