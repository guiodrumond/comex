package br.com.alura.comex.service;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository repository = null;

    @Autowired
    public ProdutoService() {
    }

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


    public List<Produto> buscarTodos() { return (List<Produto>) repository.findAll(); }
}
