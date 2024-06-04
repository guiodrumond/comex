package br.com.alura.comex_nova_versao.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidosRequest(
        @NotNull Long clienteId,
        @NotNull List<ProdutosPedidos> produtos
) {

    @Override
    public Long clienteId() {
        return clienteId;
    }

    @Override
    public List<ProdutosPedidos> produtos() {
        return produtos;
    }
}
