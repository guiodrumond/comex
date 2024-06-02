package br.com.alura.comex.service;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ItemDePedidoRepository;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {

    @Autowired
    private final PedidoRepository pedidoRepository = null;

    @Autowired
    private final ItemDePedidoRepository itemDePedidoRepository = null;

    @Autowired
    public PedidoService() {
    }


    public void cadastrar(Pedido novoPedido) {

        if (novoPedido == null) return;

        pedidoRepository.save(novoPedido);

    }

    public Pedido BuscarPedidoPorId(Long pedidoId) {

        return pedidoRepository.findById(pedidoId).get();

    }

    public ItemDePedido cadastrarItemDePedido(ItemDePedido novoItemDePedido) {
        
        return itemDePedidoRepository.save(novoItemDePedido);
        
    }

    public Optional<Pedido> buscarPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public List<Pedido> buscarTodos() { return (List<Pedido>) pedidoRepository.findAll();}
}