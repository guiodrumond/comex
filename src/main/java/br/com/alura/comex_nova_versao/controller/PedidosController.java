package br.com.alura.comex_nova_versao.controller;

import br.com.alura.comex_nova_versao.model.*;
import br.com.alura.comex_nova_versao.service.ClienteService;
import br.com.alura.comex_nova_versao.service.PedidoService;
import br.com.alura.comex_nova_versao.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid PedidosRequest form, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {

            Optional<Cliente> cliente = clienteService.buscarClientePorId(form.clienteId());

            // Verificando se o clienteId fornecido é válido

            if (cliente.isEmpty()) {
                return ResponseEntity.badRequest().body("Não existe cliente com o ID fornecido");
            }

            // Verificando se algum produtoId fornecido é inválido

            List<ProdutosPedidos> produtos = form.produtos();

            List<Optional<Produto>> produtosEncontrados = produtos.stream()
                    .map(p -> produtoService.buscarProdutoPorId(p.produtoId()))
                    .toList();

            if (produtosEncontrados.stream().anyMatch(Optional::isEmpty)) {
                return ResponseEntity.badRequest().body("Id fornecido não encontrado");
            }

            produtos.forEach(
                    p -> p.quantidade().compareTo(produtoService.quantidadeEmEstoque(p.produtoId()))
            );


            // Checa a quantidade em estoque
            for (ProdutosPedidos p : produtos) {
                int quantidadeRequisicao = p.quantidade();
                int quantidadeEstoque = produtoService.quantidadeEmEstoque(p.produtoId());
                if (quantidadeEstoque < quantidadeRequisicao) {
                    ResponseEntity.badRequest().body("Id fornecido não encontrado");
                }
            }

            //Criando e salvando o Pedido

            Pedido novoPedido = new Pedido();

            novoPedido.setCliente(cliente.get());

            if (pedidoService.getNumeroDePedidos(cliente.get().getId()) >= 5) {
                novoPedido.setTipoDesconto(TipoDescontoPedido.FIDELIDADE);
                novoPedido.setDesconto(new BigDecimal("0.05"));
            } else {
                novoPedido.setTipoDesconto(TipoDescontoPedido.NENHUM);
                novoPedido.setDesconto(BigDecimal.ZERO);
            }

            pedidoService.cadastrar(novoPedido);

            //Salvando os Itens de pedido

            List<ItemDePedido> itensDePedido;

            produtos.stream().forEach(
                    p -> {
                        ItemDePedido item = new ItemDePedido(
                                Integer.valueOf(p.quantidade()),
                                produtoService.buscarProdutoPorId(p.produtoId()).get()
                        );

                        item.setPedido(pedidoService.buscarPedidoPorId(novoPedido.getId()));

                        pedidoService.cadastrarItemDePedido(item);
                    });


            PedidoResponse pedidoResponse = new PedidoResponse(

                    novoPedido.getId(),
                    novoPedido.getData(),
                    novoPedido.getCliente().getId(),
                    novoPedido.getDesconto(),
                    novoPedido.getTipoDesconto());
                    novoPedido.getItensDePedido();

            return ResponseEntity.ok().body(pedidoResponse);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

}
