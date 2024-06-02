package br.com.alura.comex_nova_versao.controller;

import br.com.alura.comex_nova_versao.model.Produto;
import br.com.alura.comex_nova_versao.model.ProdutoRequest;
import br.com.alura.comex_nova_versao.model.ProdutoResponse;
import br.com.alura.comex_nova_versao.service.CategoriaService;
import br.com.alura.comex_nova_versao.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid ProdutoRequest form, BindingResult result) {
        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Produto novoProduto = form.toEntity(categoriaService);
            produtoService.cadastrar(novoProduto);
            ProdutoResponse produtoResponse = new ProdutoResponse(
                    novoProduto.getId(),
                    novoProduto.getNome(),
                    novoProduto.getDescricao(),
                    novoProduto.getPrecoUnitario(),
                    novoProduto.getQuantidade(),
                    novoProduto.getCategoria());

            return ResponseEntity.ok().body(produtoResponse);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/todos")
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
