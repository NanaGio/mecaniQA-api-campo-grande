package br.com.mecaniQA.api.service;

import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.PedidoPecas;
import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;
import br.com.mecaniQA.api.repository.PedidoPecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoPecasService {
    private final PedidoPecaRepository repository;

    public PedidoPecasService(PedidoPecaRepository repository) {
        this.repository = repository;
    }


    // GET ALL
    public List<PedidoPecas> getAllPedidoPecas() {
        List<PedidoPecas> lista = repository.findAll();

        if(lista.isEmpty()) {
            System.out.println("A lista está vazia.");
        }
        return lista;
    }

    // GET BY ID
    public Optional<PedidoPecas> getPedidoPecasById(Long id) {
        return repository.findById(id);
    }

    // US03 Criar um Pedido [POST /api/pedidos].
    public PedidoPecas adicionarPedido(PedidoPecas pedidoPecas) {
        if (pedidoPecas.getStatus() == null) {
            pedidoPecas.setStatus(StatusPedidoPecas.ORCANDO);
        }
        return repository.save(pedidoPecas);
    }

    // US04 - Adicionar peças a um pedido existente. [POST /api/pedidos/{idPedido}/item]
    public PedidoPecas addItemAoPedido(Long idPedido, ItemPedido itemPedido) {
        if (idPedido == null || itemPedido == null) {
            return null;
        }

        // Validação da quantidade mínima
        if (itemPedido.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade da peça deve ser maior que zero.");
        }
        return repository.addItemAoPedido(idPedido, itemPedido);
    }

    // US05 - Modificar o status do Pedido. [PUT /api/pedidos/{idPedido}/status]
    public PedidoPecas modificarStatus(Long idPedido, StatusPedidoPecas novoStatus) {
        if (idPedido == null || novoStatus == null) {
            return null;
        }
        return repository.updateStatus(idPedido, novoStatus);
    }
}
