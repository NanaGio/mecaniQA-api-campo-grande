package br.com.mecaniQA.api.service;
import br.com.mecaniQA.api.controllers.PecaController;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.repository.PecaRepository;
import java.util.List;

// Referência: How to create an REST API in Spring boot using Java (Daniel Smidstrup | YTB)

public class PecaService {
    private final PecaRepository repository = PecaRepository.getInstance();

    // GET ALL
    public List<Peca> GetAllPecas(){
        List<Peca> lista = repository.findAllpecas();

        if(lista.isEmpty()){
            System.out.println("A lista está vazia.");
        }

        return lista;
    }

    // GET BY ID
    public Peca getById(Integer idPeca){
        return repository.findPecaById(idPeca);
    }

}
