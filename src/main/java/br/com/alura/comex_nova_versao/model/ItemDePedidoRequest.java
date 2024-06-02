package br.com.alura.comex_nova_versao.model;

import br.com.alura.comex_nova_versao.service.PedidoService;
import br.com.alura.comex_nova_versao.service.ProdutoService;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemDePedidoRequest(
        Long pedidoId,
        @NotNull Long produtoId,
        @NotNull Integer quantidade,
        BigDecimal desconto,
        TipoDescontoItemPedido tipoDesconto
) {

    public ItemDePedido toEntity(ProdutoService produtoService, PedidoService pedidoService) {

        Produto produto = produtoService.BuscarProdutoPorId(produtoId);

        Pedido pedido = pedidoService.BuscarPedidoPorId(pedidoId);

        ItemDePedido itemDePedido = new ItemDePedido();

        itemDePedido.setProduto(produto);
        itemDePedido.setPedido(pedido);
        itemDePedido.setPrecoUnitario(produto.getPrecoUnitario());
        itemDePedido.setQuantidade(quantidade);
        itemDePedido.setDesconto(desconto);
        itemDePedido.setTipoDesconto(tipoDesconto);
        itemDePedido.setTotal(produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade)));

        return itemDePedido;
    }

}
