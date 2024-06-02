package br.com.alura.comex_nova_versao.controller;

import br.com.alura.comex_nova_versao.model.*;
import br.com.alura.comex_nova_versao.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid CategoriaRequest form, BindingResult result) {
        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Categoria novaCategoria = form.toEntity();
            service.cadastrar(novaCategoria);

            CategoriaResponse categoriaResponse = new CategoriaResponse(
                    novaCategoria.getId(),
                    novaCategoria.getNome(),
                    novaCategoria.getStatus().toString());
            return ResponseEntity.ok().body(categoriaResponse);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping("/todas")
    public List<CategoriaResponse> listarTodasCategorias() {
        List<Categoria> categorias = service.buscarTodas();
        return categorias.stream()
                .map(categoria -> new CategoriaResponse(
                        categoria.getId(),
                        categoria.getNome(),
                        categoria.getStatus().toString()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Long categoriaId){
        Optional<Categoria> categoria = service.buscarPorId(categoriaId);
        CategoriaResponse categoriaResponse;
        if (categoria.isPresent()) {
            categoriaResponse = new CategoriaResponse(
                    categoria.get().getId(),
                    categoria.get().getNome(),
                    categoria.get().getStatus().toString());
            return ResponseEntity
                    .ok()
                    .body(categoriaResponse);}
        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removePorId(@PathVariable("id") Long categoriaId) {
        Optional<Categoria> categoria = service.buscarPorId(categoriaId);
        if (categoria.isPresent()) {
            service.remover(categoriaId); // Supondo que você tenha um método "remover" no seu service
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatusCategoria(
            @PathVariable("id") Long categoriaId,
            @RequestParam("novoStatus") StatusCategoriaEnum novoStatus) {

        boolean atualizado = service.atualizarStatus(categoriaId, novoStatus);

        if (atualizado) {
            return ResponseEntity.ok("Status atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/nome")
    public ResponseEntity<String> atualizarNomeCategoria(
            @PathVariable("id") Long categoriaId,
            @RequestParam("novoNome") String novoNome) {

        boolean atualizado = service.atualizarNome(categoriaId, novoNome);

        if (atualizado) {
            return ResponseEntity.ok("Nome atualizado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
