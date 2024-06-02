package br.com.alura.comex_nova_versao.model;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CategoriaRequest(
        @NotNull @Length(min = 3) String nome) {
    public Categoria toEntity() {
        return new Categoria(nome);
    }
}
