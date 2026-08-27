package br.com.mecaniQA.api.service;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.repository.PecaRepository;

import java.math.BigDecimal;
import java.util.List;

// Referência: How to create an REST API in Spring boot using Java (Daniel Smidstrup | YTB)

public class PecaService {
    private static PecaService INSTANCE;

    private final PecaRepository repository = PecaRepository.getInstance();

    private PecaService(){
    }

    public static PecaService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new PecaService();
        }
        return INSTANCE;
    }

    // GET ALL
    public List<Peca> GetAllPecas(){
        return repository.findAllpecas();
    }

    // GET BY ID
    public Peca getById(Integer idPeca){
        return repository.findPecaById(idPeca);
    }

    //POST
    public Peca salvarPeca(Peca peca){
        return repository.postPeca(peca);
    }

    //PUT - EU QUERO atualizar os preços de custo/venda e a quantidade de uma Peça existente
    //PUT PREÇO CUSTO
    public Peca updatePrecoCusto(Integer idPeca, BigDecimal novoPrecoCusto){
        return repository.putPecaPrecoCusto(idPeca, novoPrecoCusto);
    }
    //PUT PREÇO VENDA
    public Peca updatePrecoVenda(Integer idPeca, BigDecimal novoPrecoVenda){
        return repository.putPecaPrecoVenda(idPeca, novoPrecoVenda);
    }
    //PUT QUANTIDADE
    public Peca updateQuantidade(Integer idPeca, Integer novaQuantidadeEstoque){
        return repository.putQuantidadeEstoque(idPeca, novaQuantidadeEstoque);
    }
    //DELETE
    public boolean deletarPeca(Integer idPeca){
        return repository.removerPeca(idPeca);
    }

}
