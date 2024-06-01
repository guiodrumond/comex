package br.com.alura.comex.SEMANA_8;

import br.com.alura.comex.dao.PedidoDao;
import br.com.alura.comex.model.Pedido;

import java.util.Comparator;
import java.util.List;

public class TestePedidoOrdenado {

    public static void main(String[] args) {

        PedidoDao pedidoDao = new PedidoDao();

        List<Pedido> listaDePedidos = pedidoDao.listaTodos();

        listaDePedidos.sort(Comparator.comparing(Pedido::getPrecoTotal).reversed());

        System.out.println("-----------------------------------------");
        System.out.println("Lista de Pedidos ordenados pelo valor total");
        listaDePedidos.forEach(p ->
                System.out.println("Pedido #" + p.getId() + ", Valor Total: " + p.getPrecoTotal()));

    }

}
