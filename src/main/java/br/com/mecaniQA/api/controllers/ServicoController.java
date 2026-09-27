package br.com.mecaniQA.api.controllers;

import br.com.mecaniQA.api.DTO.ServicoDTO;
import br.com.mecaniQA.api.mappers.ServicoMapper;
import br.com.mecaniQA.api.model.Servico;
import br.com.mecaniQA.api.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {

    private final ServicoService servicoService = ServicoService.getInstance();

    // GET ALL
    @GetMapping("/servicos")
    public ResponseEntity<List<ServicoDTO>> getAllServicos(){
        List<Servico> lista = servicoService.GetAllServicos();
        List<ServicoDTO> dtoList = new ArrayList<>();
        for (Servico s : lista) {
            dtoList.add(ServicoMapper.toDTO(s));
        }
        return ResponseEntity.ok(dtoList);
    }

    // GET BY ID
    @GetMapping("/{idServico}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable("idServico") Integer idServico){
        Servico servico = servicoService.getServicoById(idServico);
        if (servico == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ServicoMapper.toDTO(servico));
    }

    // POST
    @PostMapping("/salvarServico")
    public ResponseEntity<ServicoDTO> salvarServico(@RequestBody ServicoDTO dto){
        Servico servico = ServicoMapper.toEntity(dto);
        Servico servicoSalvo = servicoService.salvarServico(servico);
        return ResponseEntity.status(201).body(ServicoMapper.toDTO(servicoSalvo));
    }

    // PUT - TEMPO ESTIMADO
    @PutMapping("/updateTempoEstimado/{idServico}")
    public ResponseEntity<ServicoDTO> updateTempoEstimado(@PathVariable("idServico") Integer idServico, @RequestBody Integer novoTempoEstimado){
        Servico servicoAtualizado = servicoService.updateTempoEstimado(idServico, novoTempoEstimado);
        if (servicoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ServicoMapper.toDTO(servicoAtualizado));
    }

    // PUT - CUSTO TABELADO
    @PutMapping("/updateCustoTabelado/{idServico}")
    public ResponseEntity<ServicoDTO> updateCustoTabelado(@PathVariable("idServico") Integer idServico, @RequestBody BigDecimal novoCustoTabelado){
        Servico servicoAtualizado = servicoService.updateCustoTabelado(idServico, novoCustoTabelado);
        if (servicoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ServicoMapper.toDTO(servicoAtualizado));
    }

    // DELETE
    @DeleteMapping("/deletarServico/{idServico}")
    public ResponseEntity<Void> deleteServico(@PathVariable("idServico") Integer idServico){
        boolean removido = servicoService.deletarServico(idServico);
        if (!removido){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
