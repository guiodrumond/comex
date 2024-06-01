package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestePedidoCadastrar {

    public static void main(String[] args) {

        ClienteDao clienteDao = new ClienteDao();
        ProdutoDao produtoDao = new ProdutoDao();
        PedidoDao pedidoDao = new PedidoDao();

        Cliente cliente = clienteDao.buscaPorId(23l);

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);


        ItemDePedido itemDePedido1 = new ItemDePedido(
                produtoDao.buscaPorId(2l),
                pedido,
                33
        );

        ItemDePedido itemDePedido2 = new ItemDePedido(
                produtoDao.buscaPorId(3l),
                pedido,
                5
        );

        ItemDePedido itemDePedido3 = new ItemDePedido(
                produtoDao.buscaPorId(4l),
                pedido,
                6
        );

        List<ItemDePedido> listaDeItens = new ArrayList<>();

        pedido.getItensDePedidos().add(itemDePedido1);
        pedido.getItensDePedidos().add(itemDePedido2);
        pedido.getItensDePedidos().add(itemDePedido3);

        pedido.setItensDePedidos(listaDeItens);

        pedidoDao.cadastra(pedido);

        pedidoDao.cadastraItemDePedido(itemDePedido1);

    }

}
