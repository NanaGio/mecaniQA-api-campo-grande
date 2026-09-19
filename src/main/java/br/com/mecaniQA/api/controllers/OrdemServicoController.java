package br.com.mecaniQA.api.controllers;


import br.com.mecaniQA.api.model.OrdemServico;
import br.com.mecaniQA.api.service.OrdemServicoService;
import br.com.mecaniQA.api.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ordemservico")
public class OrdemServicoController {
    // A estrutura de rotas deve seguir o padrão RESTful (ex: /api/pecas e /api/servicos)
    // Utilizar os verbos HTTP corretos para cada ação mapeando-os com as anotações do Spring

    private final OrdemServicoService ordemServicoService = OrdemServicoService.getInstance();

    // [POST] US01 - Criar uma nova Ordem de Serviço (OS)

    // [PUT] US02 - Modificar o status da Ordem de Serviço (Aberto, Em Execução, Executado, etc.)
}
