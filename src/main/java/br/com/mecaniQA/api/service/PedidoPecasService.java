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
        validarId(id);
        return repository.findById(id);
    }

    // US03 Criar um Pedido [POST /api/pedidos].
    public PedidoPecas adicionarPedido(PedidoPecas pedidoPecas) {
        if (pedidoPecas == null) {
            throw new RuntimeException("Os dados do pedido são obrigatórios.");
        }
        if (pedidoPecas.getStatus() == null) {
            pedidoPecas.setStatus(StatusPedidoPecas.ORCANDO);
        }
        if (pedidoPecas.getItens() != null) {
            for (ItemPedido item : pedidoPecas.getItens()) {
                validarItem(item);
            }
        }
        return repository.save(pedidoPecas);
    }

    // US04 - Adicionar peças a um pedido existente. [POST /api/pedidos/{idPedido}/item]
    public PedidoPecas addItemAoPedido(Long idPedido, ItemPedido itemPedido) {
        validarId(idPedido);
        validarItem(itemPedido);

        if (!repository.existsById(idPedido)) {
            throw new RuntimeException("Pedido não encontrado: " + idPedido);
        }
        return repository.addItemAoPedido(idPedido, itemPedido);
    }

    // US05 - Modificar o status do Pedido. [PUT /api/pedidos/{idPedido}/status]
    public PedidoPecas modificarStatus(Long idPedido, StatusPedidoPecas novoStatus) {
        validarId(idPedido);
        if (novoStatus == null) {
            throw new RuntimeException("O novo status do pedido é obrigatório.");
        }
        if (!repository.existsById(idPedido)) {
            throw new RuntimeException("Pedido não encontrado: " + idPedido);
        }
        return repository.updateStatus(idPedido, novoStatus);
    }

    public boolean deletarPedido(Long idPedido){
        validarId(idPedido);
        if (!repository.existsById(idPedido)){
            throw new RuntimeException("Pedido não encontrado: " + idPedido);
        }
        repository.deleteById(idPedido);
        return true;
    }

    // -- VALIDAÇÕES CUSTOMIZADAS --

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("O ID do pedido deve ser maior que zero.");
        }
    }

    private void validarItem(ItemPedido item) {
        if (item == null) {
            throw new RuntimeException("Os dados do item são obrigatórios.");
        }
        if (item.getQuantidade() <= 0) {
            throw new RuntimeException("A quantidade da peça deve ser maior que zero.");
        }
        if (item.getPeca() == null || item.getPeca().getIdPeca() == null) {
            throw new RuntimeException("O item deve informar a peça (idPeca).");
        }
    }

}
