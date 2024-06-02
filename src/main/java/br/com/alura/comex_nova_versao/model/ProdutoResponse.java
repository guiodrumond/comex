package br.com.alura.comex_nova_versao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private CategoriaResponse categoria;

    public ProdutoResponse(Long id, String nome, String descricao, BigDecimal precoUnitario, Integer quantidade, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.categoria = new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getStatus().toString());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponse categoria) {
        this.categoria = categoria;
    }


}
