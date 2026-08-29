package br.com.mecaniQA.api.controllers;

import br.com.mecaniQA.api.model.Servico;
import br.com.mecaniQA.api.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/servicos")
public class ServicoController {
    // A estrutura de rotas deve seguir o padrão RESTful (ex: /api/pecas e /api/servicos)
    // Utilizar os verbos HTTP corretos para cada ação mapeando-os com as anotações do Spring

    private final ServicoService servicoService = ServicoService.getInstance();
    //GET ALL
    @GetMapping("/allServicos")
    public ResponseEntity<List<Servico>> getAllServicos(){
        List<Servico> lista = servicoService.GetAllServicos();
        return ResponseEntity.ok(lista);
    }
    //GET BY ID
    @GetMapping("/{idServico}")
    public ResponseEntity<Servico> getServicoById(@PathVariable("idServico") Integer idServico){
        Servico servico = servicoService.getServicoById(idServico);
        if (servico == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servico);
    }
    //POST
    @PostMapping("/salvarServico")
    public ResponseEntity<Servico> salvarServico(@RequestBody Servico servico){
        Servico servicoSalvo = servicoService.salvarServico(servico);
        return ResponseEntity.status(201).body(servicoSalvo);
    }


    //PUT - TEMPO ESTIMADO
    @PutMapping("/updateTempoEstimado/{idServico}")
    public ResponseEntity<Servico> updateTempoEstimado(@PathVariable("idServico") Integer idServico, @RequestBody Integer novoTempoEstimado){
        Servico servicoAtualizado = servicoService.updateTempoEstimado(idServico, novoTempoEstimado);
        if (servicoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicoAtualizado);
    }

    //PUT - CUSTO TABELADO
    @PutMapping("/updateCustoTabelado/{idServico}")
    public ResponseEntity<Servico> updateCustoTabelado(@PathVariable("idServico") Integer idServico, @RequestBody BigDecimal novoCustoTabelado){
        Servico servicoAtualizado = servicoService.updateCustoTabelado(idServico, novoCustoTabelado);
        if (servicoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicoAtualizado);
    }
    //DELETE
    @DeleteMapping("/deletarServico/{idServico}")
    public ResponseEntity<Void> deleteServico(@PathVariable("idServico") Integer idServico){
        boolean removido = servicoService.deletarServico(idServico);
        if (!removido){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
