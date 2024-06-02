package br.com.alura.comex_nova_versao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusCategoriaEnum status = StatusCategoriaEnum.ATIVO;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{\n" +
                "id=" + id + ",\n" +
                "nome='" + nome + '\'' + ",\n" +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCategoriaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusCategoriaEnum status) {
        this.status = status;
    }

}
