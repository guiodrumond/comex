package br.com.alura.comex_nova_versao.model;

import br.com.alura.comex_nova_versao.service.PedidoService;
import br.com.alura.comex_nova_versao.service.ProdutoService;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Optional;

public record ItemDePedidoRequest(
        Long pedidoId,
        @NotNull Long produtoId,
        @NotNull Integer quantidade,
        BigDecimal desconto,
        TipoDescontoItemPedido tipoDesconto
) {

    public ItemDePedido toEntity(ProdutoService produtoService, PedidoService pedidoService) {

        Optional<Produto> produto = produtoService.buscarProdutoPorId(produtoId);



        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);

        ItemDePedido itemDePedido = new ItemDePedido();

        itemDePedido.setProduto(produto);
        itemDePedido.setPedido(pedido);
        itemDePedido.setPrecoUnitario(produto.get().getPrecoUnitario());
        itemDePedido.setQuantidade(quantidade);
        itemDePedido.setDesconto(desconto);
        itemDePedido.setTipoDesconto(tipoDesconto);
        itemDePedido.setTotal(produto.get().getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade)));

        return itemDePedido;
    }

}
