package br.com.alura.comex_nova_versao.model;

import br.com.alura.comex_nova_versao.service.CategoriaService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Optional;

public record ProdutoRequest(
        @NotNull @Length(min = 3) String nome,
        String descricao,
        @NotNull @Positive BigDecimal precoUnitario,
        @NotNull @Positive Integer quantidadeEmEstoque,
        @NotNull Long categoriaId
) {
    public Produto toEntity(CategoriaService categoriaService) throws IllegalArgumentException {
        Optional<Categoria> optionalCategoria = categoriaService.buscarPorId(this.categoriaId);

        if (optionalCategoria.isEmpty())
            throw new IllegalArgumentException("O ID de categoria informado não existe, id: " + this.categoriaId);

        return new Produto(
                this.nome,
                this.descricao,
                this.precoUnitario,
                this.quantidadeEmEstoque,
                optionalCategoria.get());

    }
}