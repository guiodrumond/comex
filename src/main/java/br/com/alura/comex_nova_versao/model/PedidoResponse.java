package br.com.alura.comex_nova_versao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoResponse {
    private Long id;
    private LocalDate data;
    private Long clienteId;
    private BigDecimal desconto;
    private String tipoDesconto;

    public PedidoResponse(Long id, LocalDate data, Long clienteId, BigDecimal desconto, TipoDescontoPedido tipoDesconto) {
        this.id = id;
        this.data = data;
        this.clienteId = clienteId;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }
}
