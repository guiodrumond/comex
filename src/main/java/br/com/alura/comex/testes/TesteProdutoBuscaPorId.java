package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ProdutoDao;

public class TesteProdutoBuscaPorId {

    public static void main(String[] args) {

        ProdutoDao produtoDAO = new ProdutoDao();

        System.out.println(produtoDAO.buscaPorId(1l));

    }

}
