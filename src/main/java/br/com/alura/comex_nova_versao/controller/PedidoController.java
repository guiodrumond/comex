package br.com.alura.comex_nova_versao.controller;

import br.com.alura.comex_nova_versao.model.*;
import br.com.alura.comex_nova_versao.service.ClienteService;
import br.com.alura.comex_nova_versao.service.PedidoService;
import br.com.alura.comex_nova_versao.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid PedidoRequest form, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Pedido novoPedido = form.toEntity(clienteService);
            pedidoService.cadastrar(novoPedido);
            PedidoResponse pedidoResponse = new PedidoResponse(

                    novoPedido.getId(),
                    novoPedido.getData(),
                    novoPedido.getCliente().getId(),
                    novoPedido.getDesconto(),
                    novoPedido.getTipoDesconto());

            return ResponseEntity.ok().body(pedidoResponse);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping ("/{id}/item-de-pedido")
    public ResponseEntity cadastraItemPedido(@PathVariable("id") Long pedidoId, @RequestBody @Valid ItemDePedidoRequest form, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().build();
        }

        if (pedidoService.buscarPorId(pedidoId).isEmpty()) {
            return ResponseEntity.badRequest().body("O pedido com o ID fornecido não existe.");
        }

        if (form.pedidoId() != null && !pedidoId.equals(form.pedidoId())) {
            return ResponseEntity.badRequest().body("O pedidoId no JSON não corresponde ao ID do pedido na URL.");
        }

        ItemDePedidoRequest novoForm = new ItemDePedidoRequest(
                pedidoId,
                form.produtoId(),
                form.quantidade(), form.desconto(),
                form.tipoDesconto());

        try {
            ItemDePedido novoItemDePedido = novoForm.toEntity(produtoService, pedidoService);
            pedidoService.cadastrarItemDePedido(novoItemDePedido);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

}
