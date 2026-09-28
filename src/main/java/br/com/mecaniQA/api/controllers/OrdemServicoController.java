package br.com.mecaniQA.api.controllers;

import br.com.mecaniQA.api.DTO.OrdemServicoDTO;
import br.com.mecaniQA.api.mappers.OrdemServicoMapper;
import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;
import br.com.mecaniQA.api.service.OrdemServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ordemservico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService = OrdemServicoService.getInstance();

    // [GET] ALL
    @GetMapping("/ordemServicos")
    public ResponseEntity<List<OrdemServicoDTO>> getAllOrdemServico(){
        List<OrdemServico> lista = ordemServicoService.GetAllOrdemServico();
        List<OrdemServicoDTO> dtoList = new ArrayList<>();
        for (OrdemServico ordem : lista){
            dtoList.add(OrdemServicoMapper.toDTO(ordem));
        }
        return ResponseEntity.ok(dtoList);
    }

    // [GET] BY ID
    @GetMapping("/ordemServico/{id}")
    public ResponseEntity<OrdemServicoDTO> getOrdemServicoById(@PathVariable("id") Long id){
        OrdemServico ordem = ordemServicoService.getOrdemServicoById(id);

        if (ordem == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(OrdemServicoMapper.toDTO(ordem));
    }

    // [POST] US01 - Criar uma nova Ordem de Serviço (OS)
    @PostMapping("/salvarOrdemServico")
    public ResponseEntity<OrdemServicoDTO> criarOrdemServico(@RequestBody OrdemServicoDTO dto){
        OrdemServico ordemServico = OrdemServicoMapper.toEntity(dto);
        OrdemServico ordemSalva = ordemServicoService.criarOrdem(ordemServico);
        return ResponseEntity.status(201).body(OrdemServicoMapper.toDTO(ordemSalva));
    }

    // [PUT] US02 - Modificar o status da Ordem de Serviço (Aberto, Em Execução, Executado, etc.)
    @PutMapping("/updateOrdemServico/{id}")
    public ResponseEntity<OrdemServicoDTO> updateOrdemServicoStatus(@PathVariable("id") Long id, @RequestBody StatusOrdemServico status){
        OrdemServico ordemServicoAtualizado = ordemServicoService.modificarOrdem(id, status);
        if (ordemServicoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrdemServicoMapper.toDTO(ordemServicoAtualizado));
    }

    //DELETE
    @DeleteMapping("/deleteOrdemServico/{id}")
    public ResponseEntity<Void> deleteOrdemServico(@PathVariable("id") Long id){
        boolean removida = ordemServicoService.deletarOrdem(id);

        if (!removida){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
