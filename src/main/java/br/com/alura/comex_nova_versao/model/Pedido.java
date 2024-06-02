package br.com.alura.comex_nova_versao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_do_pedido", nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "desconto", nullable = true, scale = 2)
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "tipo_desconto", nullable = true)
    @Enumerated(EnumType.STRING)
    private TipoDescontoPedido tipoDesconto = TipoDescontoPedido.NENHUM;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDePedido> itensDePedido = new ArrayList<>();

    public Pedido(Cliente cliente, BigDecimal desconto, TipoDescontoPedido tipoDesconto) {
        this.cliente = cliente;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    @PrePersist
    public void prePersist() {
        this.data = LocalDate.now();
    }

    public Pedido() {
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

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDescontoPedido getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoPedido tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public List<ItemDePedido> getItensDePedido() {
        return itensDePedido;
    }

    public void setItensDePedido(List<ItemDePedido> itensDePedido) {
        this.itensDePedido = itensDePedido;
    }

    @Override
    public String toString() {
        return "Pedido{" + "\n" +
                "id=" + id + ",\n" +
                "data=" + data + ",\n" +
                "cliente=" + cliente + ",\n" +
                "itensDePedido=" + itensDePedido + "\n" +
                '}';
    }

}
