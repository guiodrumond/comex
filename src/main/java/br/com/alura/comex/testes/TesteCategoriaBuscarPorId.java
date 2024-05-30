package br.com.alura.comex.testes;

import br.com.alura.comex.dao.CategoriaDao;

public class TesteCategoriaBuscarPorId {

    public static void main(String[] args) {

        CategoriaDao categoriaDAO = new CategoriaDao();

        System.out.println(categoriaDAO.buscaPorId(2l));

    }

}
