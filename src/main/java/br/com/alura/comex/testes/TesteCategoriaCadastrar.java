package br.com.alura.comex.testes;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;

public class TesteCategoriaCadastrar {

    public static void main(String[] args) {
        Categoria categoriaCelular = new Categoria("CELULAR", true);
        Categoria categoriaLivro = new Categoria("LIVRO", true);
        Categoria categoriaBrinquedos = new Categoria("BRINQUEDO", false);

        CategoriaDao categoriaDAO = new CategoriaDao();

        categoriaDAO.cadastra(categoriaCelular);
        categoriaDAO.cadastra(categoriaLivro);
        categoriaDAO.cadastra(categoriaBrinquedos);

        System.out.println(categoriaDAO.listaTodas());

    }

}
