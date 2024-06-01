package br.com.alura.comex.testes;

import br.com.alura.comex.dao.PedidoDao;

import java.time.LocalDate;

public class TesteBuscarPedidoPorData {

    public static void main(String[] args) {

        PedidoDao pedidoDao = new PedidoDao();

        System.out.println(pedidoDao.buscaPorData("2024-05-31"));

    }
}
