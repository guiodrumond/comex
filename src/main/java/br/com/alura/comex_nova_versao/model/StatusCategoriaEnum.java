package br.com.alura.comex_nova_versao.model;

public enum StatusCategoriaEnum {

    ATIVO(Boolean.TRUE),
    INATIVO(Boolean.FALSE);

    private Boolean status;

    StatusCategoriaEnum(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}