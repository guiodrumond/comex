package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Produto;

public class TesteProdutoListaIndisponiveis {

    public static void main(String[] args) {

        ProdutoDao produtoDAO = new ProdutoDao();

        System.out.println(produtoDAO.listaIndisponiveis());

    }

}
