package br.com.mecaniQA.api.controllers;
import br.com.mecaniQA.api.DTO.PecaDTO;
import br.com.mecaniQA.api.mappers.PecaMapper;
import br.com.mecaniQA.api.model.Peca;
import br.com.mecaniQA.api.service.PecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/peca")
public class PecaController {

    // Isso aqui conta como injeção de dependência? Acho que não já que não é @Autowired
    private final PecaService pecaService = PecaService.getInstance();

    //GET ALL
    @GetMapping("/pecas")
    public ResponseEntity<List<PecaDTO>> getAllPecas(){
        List<Peca> lista = pecaService.GetAllPecas();
        List<PecaDTO> dtoList = new ArrayList<>();
        for (Peca p : lista) {
            dtoList.add(PecaMapper.toDTO(p));
        }
        return ResponseEntity.ok(dtoList);
    }

    // GET BY ID
    @GetMapping("/{idPeca}")
    public ResponseEntity<PecaDTO> GetPecaById(@PathVariable("idPeca") Integer idPeca){
        Peca peca = pecaService.getById(idPeca);
        if (peca == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PecaMapper.toDTO(peca));
    }

    //POST
    @PostMapping("/salvarPeca")
    public ResponseEntity<PecaDTO> SalvarPeca(@RequestBody PecaDTO dto){
        Peca peca = PecaMapper.toEntity(dto);
        Peca pecaSalva = pecaService.salvarPeca(peca);
        return ResponseEntity.status(201).body(PecaMapper.toDTO(pecaSalva));
    }

    //PUT -- EU QUERO atualizar os preços de custo/venda e a quantidade de uma Peça existente
    // PUT - PREÇO CUSTO
    @PutMapping("/updatePrecoCusto/{idPeca}")
    public ResponseEntity<PecaDTO> updatePecaCusto(@PathVariable("idPeca") Integer idPeca,
                                                 @RequestBody BigDecimal novoPrecoCusto){
        Peca pecaAtualizada = pecaService.updatePrecoCusto(idPeca, novoPrecoCusto);
        if (pecaAtualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PecaMapper.toDTO(pecaAtualizada));
    }

    // PUT - PREÇO VENDA
    @PutMapping("/updatePrecoVenda/{idPeca}")
    public ResponseEntity<PecaDTO> updatePecaVenda(@PathVariable("idPeca") Integer idPeca,
                                                 @RequestBody BigDecimal novoPrecoVenda){
        Peca pecaAtualizada = pecaService.updatePrecoVenda(idPeca, novoPrecoVenda);
        if (pecaAtualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PecaMapper.toDTO(pecaAtualizada));
    }

    // PUT - QUANTIDADE
    @PutMapping("/updateQuantidade/{idPeca}")
    public ResponseEntity<PecaDTO> updateQuantidadeEstoque(@PathVariable("idPeca") Integer idPeca,
                                                         @RequestBody Integer novaQuantidadeEstoque){
        Peca pecaAtualizada = pecaService.updateQuantidade(idPeca, novaQuantidadeEstoque);
        if (pecaAtualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PecaMapper.toDTO(pecaAtualizada));
    }
    //DELETE
    @DeleteMapping("/deletarPeca/{idPeca}")
    public ResponseEntity<Void> deletePeca(@PathVariable("idPeca") Integer idPeca){
        boolean removido = pecaService.deletarPeca(idPeca);
        if (!removido){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

