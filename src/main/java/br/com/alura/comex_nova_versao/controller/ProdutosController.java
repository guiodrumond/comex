package br.com.alura.comex_nova_versao.controller;

import br.com.alura.comex_nova_versao.model.Produto;
import br.com.alura.comex_nova_versao.model.ProdutoResponse;
import br.com.alura.comex_nova_versao.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoResponse> listarTodosProdutos() {
        List<Produto> produtos = produtoService.buscarTodas();
        return produtos.stream()
                .map(produto -> new ProdutoResponse(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPrecoUnitario(),
                        produto.getQuantidade(),
                        produto.getCategoria()
                )).collect(Collectors.toList());
    }

}
