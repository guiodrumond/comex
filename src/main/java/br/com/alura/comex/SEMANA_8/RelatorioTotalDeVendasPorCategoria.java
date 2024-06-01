package br.com.alura.comex.SEMANA_8;

import br.com.alura.comex.dao.PedidoDao;

public class RelatorioTotalDeVendasPorCategoria {

    public static void main(String[] args) {

        PedidoDao pedidoDao = new PedidoDao();

        pedidoDao.totalDeVendasPorCategoria();

    }

}
