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

        categoria1 = categoriaDAO.buscaPorId(1l);

        Produto produto1 = new Produto(
                "Samsung A1000"
                , "10Gb de memória"
                , 1230.99
                , 120
                , categoria1);

        Produto produto2 = new Produto(
                "Motorola XV"
                , "1000Gb de memória"
                , 1530.99
                , 0
                , categoria1);

        Produto produto3 = new Produto(
                "Xiomi Zeta"
                , "Entrada para 4 chips"
                , 2331.89
                , 70
                , categoria1);


        produtoDAO.cadastra(produto1);
        produtoDAO.cadastra(produto2);
        produtoDAO.cadastra(produto3);

        System.out.println(produtoDAO.listaTodos());

    }

}
