package br.com.alura.comex.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    @Column(name = "bairro", length = 200, nullable = false)
    private String bairro;
    @Column(name = "cidade", length = 200, nullable = false)
    private String cidade;
    @Column(name = "complemento", length = 100, nullable = true)
    private String complemento;
    @Column(name = "estado", length = 30, nullable = false)
    private String estado;
    @Column(name = "logradouro", length = 300, nullable = false)
    private String logradouro;
    @Column(name = "numero", length = 10, nullable = false)
    private Integer numero;

    public Endereco() {
    }

    public Endereco(String bairro, String cidade, String estado, String logradouro, Integer numero) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.logradouro = logradouro;
        this.numero = numero;
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

    @Override
    public String toString() {
        return "Endereco{" + "\n" +
                "   logradouro='" + logradouro + "',\n" +
                "   numero=" + numero + ",\n" +
                "   complemento='" + complemento + "',\n" +
                "   bairro='" + bairro + "',\n" +
                "   cidade='" + cidade + "',\n" +
                "   estado='" + estado + "'\n" +
                '}';
    }
}
