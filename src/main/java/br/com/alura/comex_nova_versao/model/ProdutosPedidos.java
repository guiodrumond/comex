package br.com.alura.comex_nova_versao.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutosPedidos(
        @NotNull Long produtoId,
        @NotNull @Positive Integer quantidade
) {

    @Override
    public Long produtoId() {
        return produtoId;
    }

    @Override
    public Integer quantidade() {
        return quantidade;
    }
}
