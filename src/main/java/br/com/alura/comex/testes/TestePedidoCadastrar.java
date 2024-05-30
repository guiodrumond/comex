package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;

public class TestePedidoCadastrar {

    public static void main(String[] args) {

        ProdutoDao produtoDAO = new ProdutoDao();

        Produto produto1 = produtoDAO.buscaPorId(1l);

        Pedido pedido1 = new Pedido();

        ItemDePedido itemDePedido1 = new ItemDePedido(
                produto1
                , pedido1
                , produto1.getPrecoUnitario()
                , 2
        );



    }

}
