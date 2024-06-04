package br.com.alura.comex_nova_versao.service;

import br.com.alura.comex_nova_versao.model.ItemDePedido;
import br.com.alura.comex_nova_versao.model.Pedido;
import br.com.alura.comex_nova_versao.repository.ItemDePedidoRepository;
import br.com.alura.comex_nova_versao.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ItemDePedidoRepository itemDePedidoRepository;

    public void cadastrar(Pedido novoPedido) {

        if (novoPedido == null) return;

        pedidoRepository.save(novoPedido);

    }

    public Pedido buscarPedidoPorId(Long pedidoId) {

        return pedidoRepository.findById(pedidoId).get();

    }

    public ItemDePedido cadastrarItemDePedido(ItemDePedido novoItemDePedido) {
        
        return itemDePedidoRepository.save(novoItemDePedido);
        
    }

    public Optional<Pedido> buscarPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public int getNumeroDePedidos(Long id) {
        return pedidoRepository.countByClienteId(id);
    }

    public void adicionarItemAoPedido(ItemDePedido item) {
        Pedido pedido = buscarPedidoPorId(item.getPedido().getId());

        pedido.getItensDePedido().add(item);

    }
}