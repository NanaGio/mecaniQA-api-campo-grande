package br.com.mecaniQA.api.controllers;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.service.PecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pecas")
public class PecaController {

    // Isso aqui conta como injeção de dependência? Acho que não já que não é @Autowired
    private final PecaService pecaService = PecaService.getInstance();
    //GET ALL
    @GetMapping("/pecas")
    public ResponseEntity<List<Peca>> getAllPecas(){
        List<Peca> lista = pecaService.GetAllPecas();
        return ResponseEntity.ok(lista);
    }
    // GET BY ID
    @GetMapping("/{idPeca}")
    public ResponseEntity<Object> GetPecaById(@PathVariable("idPeca") Integer idPeca){
        Peca peca = pecaService.getById(idPeca);
        if (peca == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peca);
    }
    //POST
    @PostMapping("/peca")
    public ResponseEntity<Peca> SalvarPeca(@RequestBody Peca peca){
        Peca pecaSalva = pecaService.salvarPeca(peca);
        return ResponseEntity.status(201).body(pecaSalva);
    }
    //PUT -- EU QUERO atualizar os preços de custo/venda e a quantidade de uma Peça existente
    // PUT - PREÇO CUSTO
    @PutMapping("/peca/{idPeca}")
    public ResponseEntity<Peca> updatePecaCusto(@PathVariable("idPeca") Integer idPeca, @RequestBody BigDecimal novoPrecoCusto){
        Peca pecaAtualizada = pecaService.updatePrecoCusto(idPeca, novoPrecoCusto);
        if (pecaAtualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pecaAtualizada);
    }
    // PUT - PREÇO VENDA
    @PutMapping("/peca/{idPeca}")
    public ResponseEntity<Peca> updatePecaVenda(@PathVariable("idPeca") Integer idPeca, @RequestBody BigDecimal novoPrecoVenda){
        Peca pecaAtualizada = pecaService.updatePrecoVenda(idPeca, novoPrecoVenda);
        if (pecaAtualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pecaAtualizada);
    }
    // PUT - QUANTIDADE
    @PutMapping("/peca/{idPeca}")
    public ResponseEntity<Peca> updateQuantidadeEstoque(@PathVariable("idPeca") Integer idPeca, @RequestBody Integer novaQuantidadeEstoque){
        Peca pecaAtualizada = pecaService.updateQuantidade(idPeca, novaQuantidadeEstoque);
        if (pecaAtualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pecaAtualizada);
    }
    //DELETE
    @DeleteMapping("/peca/{idPeca}")
    public ResponseEntity<Void> deletePeca(@PathVariable("idPeca") Integer idPeca){
        boolean removido = pecaService.deletarPeca(idPeca);
        if (!removido){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

