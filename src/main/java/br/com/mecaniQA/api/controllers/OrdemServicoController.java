package br.com.mecaniQA.api.controllers;


import br.com.mecaniQA.api.DTO.OrdemServicoDTO;
import br.com.mecaniQA.api.mappers.OrdemServicoMapper;
import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.model.enums.StatusOrdemServico;
import br.com.mecaniQA.api.service.OrdemServicoService;
import br.com.mecaniQA.api.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/ordemservico")
public class OrdemServicoController {
    // A estrutura de rotas deve seguir o padrão RESTful (ex: /api/pecas e /api/servicos)
    // Utilizar os verbos HTTP corretos para cada ação mapeando-os com as anotações do Spring

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

    @GetMapping("/ordemServico/{id}")
    public ResponseEntity<OrdemServicoDTO> getOrdemServicoById(@PathVariable("id") Long id){
        OrdemServico ordem = OrdemServicoService.getInstance().getOrdemServicoById(id);

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
    @PutMapping("/upadateOrdemServico/{id}")
    public ResponseEntity<OrdemServicoDTO> updateOrdemServicoStatus(@PathVariable("id") Long id, @RequestBody StatusOrdemServico status){
        OrdemServico ordemServicoAtualizado = OrdemServicoService.getInstance().modificarOrdem(id, status);
        if (ordemServicoAtualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrdemServicoMapper.toDTO(ordemServicoAtualizado));
    }
}
