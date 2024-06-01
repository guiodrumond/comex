package br.com.alura.comex.testes;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;

public class TesteProdutoCadastrar {

    public static void main(String[] args) {

        ProdutoDao produtoDAO = new ProdutoDao();
        CategoriaDao categoriaDAO = new CategoriaDao();

        Categoria categoria1 = new Categoria();

        categoria1 = categoriaDAO.buscaPorId(2l);

        Produto produto1 = new Produto(
                "Produto 65654"
                , "10Gb de memória"
                , 12360.99
                , 120
                , categoria1);

        Produto produto2 = new Produto(
                "Motorola HGHGH"
                , "1000Gb de memória"
                , 530.99
                , 20
                , categoria1);

        Produto produto3 = new Produto(
                "Alicate Zeta"
                , "Entrada para 4 chips"
                , 23.89
                , 70
                , categoria1);


        produtoDAO.cadastra(produto1);
        produtoDAO.cadastra(produto2);
        produtoDAO.cadastra(produto3);

        System.out.println(produtoDAO.listaTodos());

    }

}
