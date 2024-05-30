package br.com.alura.comex.testes;

import br.com.alura.comex.dao.CategoriaDao;

public class RelatorioVendasPorCategoria {

    public static void main(String[] args) {

        CategoriaDao categoriaDao = new CategoriaDao();

        categoriaDao.vendasPorCategoria();

    }

}
