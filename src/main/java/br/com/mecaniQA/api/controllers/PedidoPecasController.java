package br.com.mecaniQA.api.controllers;

import br.com.mecaniQA.api.DTO.ItemPedidoDTO;
import br.com.mecaniQA.api.DTO.PedidoPecasDTO;
import br.com.mecaniQA.api.mappers.ItemPedidoMapper;
import br.com.mecaniQA.api.mappers.PedidoPecasMapper;
import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.PedidoPecas;
import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;
import br.com.mecaniQA.api.service.PedidoPecasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoPecasController {
    private final PedidoPecasService pedidoPecasService;

    // Declarando Controller
    public PedidoPecasController(PedidoPecasService pedidoPecasService) {
        this.pedidoPecasService = pedidoPecasService;
    }

    // GET ALL
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoPecasDTO>> getAllPedidos(){
        List<PedidoPecas> lista = pedidoPecasService.getAllPedidoPecas();
        List<PedidoPecasDTO> dtoList = new ArrayList<>();
        for (PedidoPecas p : lista) {
            dtoList.add(PedidoPecasMapper.toDTO(p));
        }
        return ResponseEntity.ok(dtoList);
    }

    // GET BY ID
    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoPecasDTO> getPedidoById(@PathVariable("idPedido") Long idPedido) {
        Optional<PedidoPecas> pedidoPecas = pedidoPecasService.getPedidoPecasById(idPedido);
        return pedidoPecas
                .map(pedido -> ResponseEntity.ok(PedidoPecasMapper.toDTO(pedido)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // [POST] US03 - Criar um novo Pedido de Peças
    @PostMapping("/salvarPedido")
    public ResponseEntity<PedidoPecasDTO> criarPedido(@RequestBody PedidoPecasDTO dto) {
        // Converte DTO para Model
        PedidoPecas novoPedido = PedidoPecasMapper.toEntity(dto);
        // Chama a regra de negócio do Service
        PedidoPecas pedidoSalvo = pedidoPecasService.adicionarPedido(novoPedido);
        // Retorna 201 Created com o DTO do pedido criado
        return ResponseEntity.status(201).body(PedidoPecasMapper.toDTO(pedidoSalvo));
    }

    // [POST] US04 - Adicionar peças e quantidades a um pedido existente
    @PostMapping("/{idPedido}/item")
    public ResponseEntity<PedidoPecasDTO> adicionarItemAoPedido(
            @PathVariable("idPedido") Long idPedido,
            @RequestBody ItemPedidoDTO itemDto) {

        // 1. Converte o DTO do item para o Model
        ItemPedido item = ItemPedidoMapper.toEntity(itemDto);

        // 2. Chama o Service para anexar o item ao pedido
        PedidoPecas pedidoAtualizado = pedidoPecasService.addItemAoPedido(idPedido, item);

        // 3. Se o pedido não existir, retorna 404 Not Found
        if (pedidoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        // 4. Retorna 200 OK com o Pedido completo e a lista de itens atualizada
        return ResponseEntity.ok(PedidoPecasMapper.toDTO(pedidoAtualizado));
    }

    // [PUT] US05 - Modificar o status do Pedido (Orçando, Entregue, Pago/Faturado)
    @PutMapping("/{idPedido}/status")
    public ResponseEntity<PedidoPecasDTO> modificarStatus(
            @PathVariable("idPedido") Long idPedido,
            @RequestBody StatusPedidoPecas novoStatus) {

        PedidoPecas pedidoAtualizado = pedidoPecasService.modificarStatus(idPedido, novoStatus);

        if (pedidoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(PedidoPecasMapper.toDTO(pedidoAtualizado));
    }

    @DeleteMapping("/deletarPedido/{idPedido}")
    public ResponseEntity<Void> deletarPedido(@PathVariable("idPedido") Long idPedido){
        boolean removido = pedidoPecasService.deletarPedido(idPedido);
        if (!removido){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
