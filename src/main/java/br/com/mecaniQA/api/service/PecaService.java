package br.com.mecaniQA.api.service;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.repository.PecaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

// Referência: How to create an REST API in Spring boot using Java (Daniel Smidstrup | YTB)
@Service
public class PecaService {
    private final PecaRepository repository;

    public PecaService(PecaRepository repository){
        this.repository = repository;
    }

    // GET ALL
    public List<Peca> GetAllPecas(){
        return repository.findAll();
    }

    // GET BY ID
    public Peca getById(Integer idPeca){
        return repository.findById(idPeca)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada"));
    }

    //POST
    public Peca salvarPeca(Peca peca){
        return repository.save(peca);
    }

    //PUT - EU QUERO atualizar os preços de custo/venda e a quantidade de uma Peça existente
    //PUT PREÇO CUSTO
    // PUT - preço de custo
    public Peca updatePrecoCusto(Integer idPeca, BigDecimal novoPrecoCusto) {
        validarPreco(novoPrecoCusto, "Preço de custo");
        Peca peca = getById(idPeca);
        peca.setPrecoCusto(novoPrecoCusto);
        return repository.update(peca);
    }

    // PUT - preço de venda
    public Peca updatePrecoVenda(Integer idPeca, BigDecimal novoPrecoVenda) {
        validarPreco(novoPrecoVenda, "Preço de venda");
        Peca peca = getById(idPeca);
        peca.setPrecoVenda(novoPrecoVenda);
        return repository.update(peca);
    }

    // PUT - quantidade em estoque
    public Peca updateQuantidade(Integer idPeca, Integer novaQuantidade) {
        if (novaQuantidade == null || novaQuantidade < 0) {
            throw new RuntimeException("A quantidade em estoque não pode ser negativa.");
        }
        Peca peca = getById(idPeca);
        peca.setQuantidadeEstoque(novaQuantidade);
        return repository.update(peca);
    }

    //DELETE
    public boolean deletarPeca(Integer idPeca){
        if (!repository.existsById(idPeca)) {
            throw new RuntimeException("Peça não encontrada");
        }
        return repository.deleteById(idPeca);
    }

    // -- VALIDAÇÕES CUSTOMIZADAS --

    private void validarPeca(Peca peca) {
        if (peca == null) {
            throw new RuntimeException("Dados da peça não informados.");
        }
        if (peca.getCodigoDeBarras() == null || peca.getCodigoDeBarras().isBlank()
                || peca.getCodigoDeBarras().length() > 13) {
            throw new RuntimeException("Código de barras obrigatório, com até 13 caracteres.");
        }
        if (peca.getFornecedor() == null || peca.getFornecedor().isBlank()
                || peca.getFornecedor().length() > 30) {
            throw new RuntimeException("Fornecedor obrigatório, com até 30 caracteres.");
        }
        if (peca.getQuantidadeEstoque() == null || peca.getQuantidadeEstoque() < 0) {
            throw new RuntimeException("A quantidade em estoque não pode ser negativa.");
        }
        validarPreco(peca.getPrecoCusto(), "Preço de custo");
        validarPreco(peca.getPrecoVenda(), "Preço de venda");
        if (peca.getCategoriaPeca() == null) {
            throw new RuntimeException("Categoria da peça obrigatória.");
        }
        if (peca.getCor() != null && peca.getCor().length() > 15) {
            throw new RuntimeException("A cor deve ter até 15 caracteres.");
        }
    }

    private void validarPreco(BigDecimal preco, String campo) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(campo + " inválido: deve ser maior ou igual a zero.");
        }
    }


}
