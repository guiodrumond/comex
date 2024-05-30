package br.com.alura.comex.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 120, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 15, nullable = false)
    private String cpf;
    @Column(name = "email", length = 120, nullable = false)
    private String email;
    @Column(name = "profissao", length = 120, nullable = true)
    private String profissao;
    @Column(name = "telefone", length = 12, nullable = true)
    private String telefone;
    @Embedded
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente(String nome, String cpf, String email, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" + "\n" +
                "id=" + id + ",\n" +
                "nome='" + nome + "',\n" +
                "cpf='" + cpf + "',\n" +
                "email='" + email + "',\n" +
                "profissao='" + profissao + "',\n" +
                "telefone='" + telefone + "',\n" +
                "endereco=" + endereco + "\n" +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Object getEndereco() {
        return endereco;
    }

    public void setEndereco(Object endereco) {
        this.endereco = (Endereco) endereco;
    }

}
