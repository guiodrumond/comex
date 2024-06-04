package br.com.alura.comex_nova_versao.relatorios;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class VendasPorCategoriaDTO {
    private String nomeCategoria;
    private Integer quantidadeProdutos;
    private BigDecimal totalVendas;

    public VendasPorCategoriaDTO(String nome, int size, BigDecimal bigDecimalStream) {
        this.nomeCategoria = nome;
        this.quantidadeProdutos = size;
        this.totalVendas = bigDecimalStream;

    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

}
