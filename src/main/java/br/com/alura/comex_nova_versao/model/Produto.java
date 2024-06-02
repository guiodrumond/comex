package br.com.alura.comex_nova_versao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    @Column(name = "preco_unitario", length = 20, nullable = false)
    private BigDecimal precoUnitario;
    @Column(name = "quantidade", length = 8, nullable = false)
    private Integer quantidade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_categoria_produto"))
    private Categoria categoria;

    public Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal precoUnitario, Integer quantidade, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" + "\n" +
                "    nome='" + nome + "',\n" +
                "    descricao='" + descricao + "',\n" +
                "    precoUnitario=" + precoUnitario + ",\n" +
                "    quantidade=" + quantidade + "\n" +
                '}';
    }


}
