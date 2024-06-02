package br.com.alura.comex_nova_versao.model;

import br.com.alura.comex_nova_versao.service.ClienteService;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ClienteRequest(

        @NotNull @Length(min = 3, max = 120) String nome,
        @NotNull @Length(min = 11, max = 15) String cpf,
        @NotNull @Length(min = 3, max = 120) String email,
        @Length(max = 120) String profissao,
        @Length(min = 12) String telefone,
        Endereco endereco
) {
    public Cliente toEntity(ClienteService service) {

        return new Cliente(
                this.nome,
                this.cpf,
                this.email,
                this.profissao,
                this.telefone,
                this.endereco
        );
    }
}
