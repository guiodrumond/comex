package br.com.alura.comex_nova_versao.model;

import br.com.alura.comex_nova_versao.service.ClienteService;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Optional;

public record PedidoRequest(
        @NotNull Long clienteId,
        BigDecimal desconto,
        String tipoDesconto
) {


    public Pedido toEntity(ClienteService clienteService) throws IllegalArgumentException {

        Optional<Cliente> cliente = clienteService.buscarClientePorId(clienteId);

        TipoDescontoPedido tipoDescontoEnum = converterStringParaEnum(tipoDesconto);

        return new Pedido(cliente.get(), desconto, tipoDescontoEnum);
    }

    private TipoDescontoPedido converterStringParaEnum(String tipoDesconto) {
        try {
            return TipoDescontoPedido.valueOf(tipoDesconto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de desconto inválido: " + tipoDesconto);
        }
    }

}
