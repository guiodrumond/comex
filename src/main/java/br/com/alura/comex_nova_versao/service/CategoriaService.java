package br.com.alura.comex_nova_versao.service;

import br.com.alura.comex_nova_versao.model.Categoria;
import br.com.alura.comex_nova_versao.model.ItemDePedido;
import br.com.alura.comex_nova_versao.model.Pedido;
import br.com.alura.comex_nova_versao.model.StatusCategoriaEnum;
import br.com.alura.comex_nova_versao.relatorios.VendasPorCategoriaDTO;
import br.com.alura.comex_nova_versao.repository.CategoriaRepository;
import br.com.alura.comex_nova_versao.repository.PedidoRepository;
import br.com.alura.comex_nova_versao.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrar(Categoria categoria) {

        if (categoria == null) return;
        if (categoria.getNome() == null) return;

        categoriaRepository.save(categoria);

    }

    public Optional<Categoria> buscarPorId(Long categoriaId) {
        return categoriaRepository.findById(categoriaId);
    }

    public void remover(Long categoriaId) {
        Optional<Categoria> categoria = buscarPorId(categoriaId);
        categoria.ifPresent(c -> categoriaRepository.delete(c));
    }

    public List<Categoria> buscarTodas() {
        Iterable<Categoria> categorias = categoriaRepository.findAll();
        List<Categoria> listaCategorias = new ArrayList<>();
        categorias.forEach(listaCategorias::add);
        return listaCategorias;
    }

    public boolean atualizarStatus(Long categoriaId, StatusCategoriaEnum novoStatus) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        if (categoria == null) {
            return false;
        }
        categoria.setStatus(novoStatus);
        categoriaRepository.save(categoria);
        return true;
    }

    public boolean atualizarNome(Long categoriaId, String novoNome) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        if (categoria == null) {
            return false;
        }
        categoria.setNome(novoNome);
        categoriaRepository.save(categoria);
        return true;
    }

    public ResponseEntity<List<VendasPorCategoriaDTO>> vendasPorCategoria() {

        List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();

        List<ItemDePedido> itensVendidos = pedidos.stream()
                .flatMap(p -> p.getItensDePedido().stream())
                .collect(Collectors.toList());

        System.out.println("Itens vendidos: " + itensVendidos);

        List<VendasPorCategoriaDTO> vendas = itensVendidos.stream()
                .collect(Collectors.groupingBy(item -> produtoRepository.findById(item.getProdutoId()).get().getCategoria()))
                .entrySet().stream()
                .map(i ->
                        new VendasPorCategoriaDTO(
                                i.getKey().getNome(),
                                i.getValue().size(),
                                i.getValue()
                                        .stream()
                                        .map(item -> BigDecimal.valueOf(item.getQuantidade()).multiply(produtoRepository.findById(item.getProdutoId()).get().getPrecoUnitario()))
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        ))
                .collect(Collectors.toList());

        System.out.println("Vendas: " + vendas);


        return ResponseEntity.ok(vendas);

    }

}
