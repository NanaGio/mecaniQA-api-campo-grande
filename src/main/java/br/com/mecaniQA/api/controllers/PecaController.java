package br.com.mecaniQA.api.controllers;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.service.PecaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    private final PecaService pecaService;

    // Isso aqui conta como injeção de dependência? Acho que não já que não é @Autowired
    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }
    //GET ALL
    @GetMapping("/api/pecas/allPecas")
    public List<Peca> getAllPecas(){
        return pecaService.GetAllPecas();
    }
    // GET BY ID
    @GetMapping("/api/pecas/{idPeca}")
    public Peca GetPecaById(@PathVariable("idPeca") Integer idPeca){
        return pecaService.getById(idPeca);
    }
    //POST
    @PostMapping("/api/pecas/salvarPeca")
    public Peca SalvarPeca(Peca peca){
        return pecaService.salvarPeca(peca);
    }
    //PUT -- EU QUERO atualizar os preços de custo/venda e a quantidade de uma Peça existente
    // PUT - PREÇO CUSTO
    @PutMapping("/api/pecas/updatePrecoCusto/{idPeca}")
    public Peca updatePecaCusto(Peca peca){
        return pecaService.updatePrecoCusto(peca);
    }
    // PUT - PREÇO VENDA
    @PutMapping("/api/pecas/updatePrecoVenda/{idPeca}")
    public Peca updatePecaVenda(Peca peca){
        return pecaService.updatePrecoVenda(peca);
    }
    // PUT - QUANTIDADE
    @PutMapping("/api/pecas/updateQuantidade/{idPeca}")
    public Peca updateQuantidadeEstoque(Peca peca){
        return pecaService.updateQuantidade(peca);
    }
    //DELETE
    @DeleteMapping("/api/pecas/deletarPeca/{idPeca}")
    public boolean deletePeca(@PathVariable("idPeca") Integer idPeca){return pecaService.deletarPeca(idPeca);}

}

