package br.com.mecaniQA.api.controllers;

import br.com.mecaniQA.api.model.Servico;
import br.com.mecaniQA.api.service.ServicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servicos")
public class ServicoController {
    // A estrutura de rotas deve seguir o padrão RESTful (ex: /api/pecas e /api/servicos)
    // Utilizar os verbos HTTP corretos para cada ação mapeando-os com as anotações do Spring
    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }
    //GET ALL
    @GetMapping("/api/servicos/allServicos")
    public List<Servico> getAllServicos(){
        return servicoService.GetAllServicos();
    }
    //GET BY ID
    @GetMapping("/api/servico/{idServico}")
    public Servico GetServicoById(@PathVariable("idServico") Integer idServico){
        return servicoService.getServicoById(idServico);
    }
    //POST
    @PostMapping("/api/servico/salvarPeca")
    public Servico SalvarServico(Servico servico){
        return servicoService.salvarServico(servico);
    }
    //PUT - TEMPO ESTIMADO
    @PutMapping("/api/servico/updateTempoEstimado")
    public Servico updateTempoEstimado(Servico servico){
        return servicoService.updateTempoEstimado();
    }
    //PUT - CUSTO TABELADO
    @PutMapping("/api/servico/updateCustoTabelado")
    public Servico updateCustoTabelado(Servico servico){
        return servicoService.updateCustoTabelado();
    }
    //DELETE
    @DeleteMapping("/api/servico/deletarServico/{idServico}")
    public boolean deleteServico(@PathVariable("idServico")Integer idServico){
        return servicoService.deletarServico(idServico);
    }
}
