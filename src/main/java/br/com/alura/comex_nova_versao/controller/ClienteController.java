package br.com.alura.comex_nova_versao.controller;


import br.com.alura.comex_nova_versao.model.*;
import br.com.alura.comex_nova_versao.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid ClienteRequest form, BindingResult result) {

        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Cliente novoCliente = form.toEntity(service);
            service.cadastrar(novoCliente);
            return ResponseEntity.ok().build();

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
