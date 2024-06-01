package br.com.alura.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_do_pedido", nullable = false, updatable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDePedido> itensDePedidos = new ArrayList<>();

    @Column(name = "desconto", length = 20, nullable = true)
    private BigDecimal desconto;
    @Column(name = "fidelidade", length = 1, nullable = true)
    private Boolean fidelidade;

    @PrePersist
    public void prePersist() {
        this.data = LocalDate.now();
    }

    public Pedido() {
    }

    public List<ItemDePedido> getItensDePedidos() {
        return itensDePedidos;
    }

    public void setItensDePedidos(List<ItemDePedido> itensDePedidos) {
        this.itensDePedidos = itensDePedidos;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /*
    public List<ItemDePedido> getItensDePedido() {
        return itensDePedido;
    }
     */

    /*
    public void setItensDePedido(List<ItemDePedido> itensDePedido) {
        this.itensDePedido = itensDePedido;
    }
     */

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Boolean getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(Boolean fidelidade) {
        this.fidelidade = fidelidade;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", itensDePedidos=" + itensDePedidos +
                ", desconto=" + desconto +
                ", fidelidade=" + fidelidade +
                '}';
    }

    public BigDecimal getPrecoTotal() {

        List<ItemDePedido> itensDePedido = this.getItensDePedidos();

        BigDecimal precoTotal = itensDePedido.stream()
                .map(i -> i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return precoTotal;
    }

}