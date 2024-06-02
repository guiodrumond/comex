package br.com.alura.comex_nova_versao.model;

public class CategoriaResponse {

    private Long id;
    private String nome;
    private String status;

    public CategoriaResponse(Long id, String nome, String status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }
}
