package br.com.mecaniQA.api.repository;

import br.com.mecaniQA.api.model.ItemPedido;
import br.com.mecaniQA.api.model.PedidoPecas;
import br.com.mecaniQA.api.model.enums.StatusPedidoPecas;

import java.util.ArrayList;
import java.util.List;

public class PedidoPecaRepository {
    private static PedidoPecaRepository INSTANCE;
    private final List<PedidoPecas> bancoEmMemoria = new ArrayList<>();
    private Long criarId = 1L;
    private Long criarIdItem = 1L; // Auto-incremento para os itens

    private PedidoPecaRepository() {}

    public static synchronized PedidoPecaRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PedidoPecaRepository();
        }
        return INSTANCE;
    }

    // GET ALL
    public List<PedidoPecas> findAll() {
        return new ArrayList<>(bancoEmMemoria);
    }

    // GET BY ID
    public PedidoPecas findById(Long id) {
        for (PedidoPecas pedido : bancoEmMemoria) {
            if(id.equals(pedido.getId())) {
                return pedido;
            }
        }
        return null;
    }

    // US03 Criar um Pedido [POST /api/pedidos].
    public PedidoPecas postPedidoPecas(PedidoPecas pedidoPecas) {
        if(pedidoPecas.getId() == null) {
            pedidoPecas.setId(criarId++);
        }
        bancoEmMemoria.add(pedidoPecas);
        return pedidoPecas;
    }

    // US04 - Adicionar peças a um pedido existente. [POST /api/pedidos/{idPedido}/item]
    public PedidoPecas addItemAoPedido(Long idPedido, ItemPedido item) {
        PedidoPecas pedido = findById(idPedido);

        if (pedido == null) {
            return null;
        }

        if (item.getId() == null){
            item.setId(criarIdItem++);
        }

        if (pedido.getItens() == null) {
            pedido.setItens(new ArrayList<>());
        }
        pedido.getItens().add(item);

        return pedido;
    }

    // US05 - Modificar o status do Pedido. [PUT /api/pedidos/{idPedido}/status]
    public PedidoPecas updateStatus(Long idPedido, StatusPedidoPecas novoStatus) {
        for (PedidoPecas pedido : this.bancoEmMemoria) {
            if (idPedido.equals(pedido.getId())) {
                pedido.setStatus(novoStatus);
                return pedido;
            }
        }
        return null;
    }
}
