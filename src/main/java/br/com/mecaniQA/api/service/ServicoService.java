package br.com.mecaniQA.api.service;

import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.model.Servico;
import br.com.mecaniQA.api.repository.PecaRepository;
import br.com.mecaniQA.api.repository.ServicoRepository;
import java.util.List;

public class ServicoService {
    private static ServicoService INSTANCE;

    private final ServicoRepository repository = ServicoRepository.getInstance();

    private ServicoService(){

    }
    public static ServicoService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ServicoService();
        }
        return INSTANCE;
    }

    //GET ALL
    public List<Servico> GetAllServicos(){
        List<Servico> lista = repository.findAllServicos();

        if(lista.isEmpty()){
            System.out.println("A lista está vazia.");
        }
        return lista;
    }
    //GET BY ID
    public Servico getServicoById(Integer idServico){
        return repository.findServicoById(idServico);
    }
    //POST
    public Servico salvarServico(Servico servico){
        return repository.postServico(servico);
    }
    //PUT - tempoEstimado
    public Servico updateTempoEstimado(){
        return repository.putTempoEstimado();
    }
    //PUT - custoTabelado
    public Servico updateCustoTabelado(){
        return repository.putCustoTabelado();
    }
    //DELETE
    public boolean deletarServico(Integer idServico){
        return repository.removerServico(idServico);
    }


}
