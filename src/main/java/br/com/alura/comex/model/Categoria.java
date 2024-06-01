package br.com.alura.comex.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "ativa", length = 1, nullable = false)
    private Boolean ativa;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Produto> produtos;

    public Categoria() {
    }

    public Categoria(String nome, Boolean ativa) {
        this.nome = nome;
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        return "Categoria{\n" +
                "id=" + id + ",\n" +
                "nome='" + nome + '\'' + ",\n" +
                "ativa=" + ativa + "\n" +
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

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
}
