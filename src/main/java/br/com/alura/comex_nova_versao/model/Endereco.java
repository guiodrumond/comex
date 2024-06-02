package br.com.alura.comex_nova_versao.model;

import jakarta.persistence.*;

@Embeddable
public class Endereco {

    @Column(name = "bairro", length = 200, nullable = true)
    private String bairro;
    @Column(name = "cidade", length = 200, nullable = true)
    private String cidade;
    @Column(name = "complemento", length = 100, nullable = true)
    private String complemento;
    @Column(name = "estado", length = 30, nullable = true)
    private String estado;
    @Column(name = "logradouro", length = 300, nullable = true)
    private String logradouro;
    @Column(name = "numero", length = 10, nullable = true)
    private Integer numero;

    public Endereco() {
    }

    public Endereco(String bairro, String cidade, String estado, String logradouro, Integer numero) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco(String bairro, String cidade, String estado, String logradouro, Integer numero, String complemento) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return logradouro;
    }

    public void setRua(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

}
