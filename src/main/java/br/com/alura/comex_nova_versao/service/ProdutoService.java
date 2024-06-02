package br.com.alura.comex_nova_versao.service;

import br.com.alura.comex_nova_versao.model.Produto;
import br.com.alura.comex_nova_versao.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void cadastrar(Produto novoProduto) {

        if (novoProduto == null) return;

        repository.save(novoProduto);

    }

    public List<Produto> buscarTodas() {
        Iterable<Produto> produtos = repository.findAll();
        List<Produto> listaProdutos = new ArrayList<>();
        produtos.forEach(listaProdutos::add);
        return listaProdutos;
    };

    public Produto BuscarProdutoPorId(Long produtoId) {

        return repository.findById(produtoId).get();

    }

}
