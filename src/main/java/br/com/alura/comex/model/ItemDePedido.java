package br.com.alura.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_de_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Produto produto;
    @ManyToOne
    private Pedido pedido;
    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "desconto", nullable = true)
    private BigDecimal desconto;
    @Enumerated
    private TipoDescontoProdutoEnum tipoDesconto;

    public ItemDePedido() {
    }

    public ItemDePedido(Produto produto, Pedido pedido, BigDecimal precoUnitario, Integer quantidade) {
        this.produto = produto;
        this.pedido = pedido;
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDescontoProdutoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoProdutoEnum tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getTotal(){
        return this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade)).multiply((BigDecimal.ONE.subtract(this.desconto)));
    }

}
