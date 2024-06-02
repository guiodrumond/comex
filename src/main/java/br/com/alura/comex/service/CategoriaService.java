package br.com.alura.comex.service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public void cadastrar(Categoria categoria) {

        if (categoria == null) return;
        if (categoria.getNome() == null) return;

        repository.save(categoria);

    }

    public List<Categoria> buscarTodas() { return repository.findAll(); }

    public class VendasPorCategoriaDTO {
        private String nomeCategoria;
        private Long quantidadeProdutos;
        private BigDecimal totalVendas;

        public String getNomeCategoria() {
            return nomeCategoria;
        }

        public Optional<Categoria> buscarPorId(Long categoriaId) {
            return repository.findById(categoriaId);
        }

        public void remover(Long categoriaId) {
            Optional<Categoria> categoria = buscarPorId(categoriaId);
            categoria.ifPresent(c -> repository.delete(c));
        }

        public List<Categoria> buscarTodas() {
            Iterable<Categoria> categorias = repository.findAll();
            List<Categoria> listaCategorias = new ArrayList<>();
            categorias.forEach(listaCategorias::add);
            return listaCategorias;
        }

        public boolean atualizarNome(Long categoriaId, String novoNome) {
            Categoria categoria = repository.findById(categoriaId).orElse(null);
            if (categoria == null) {
                return false;
            }
            categoria.setNome(novoNome);
            repository.save(categoria);
            return true;
        }

        @Query("SELECT c FROM Categoria c WHERE c.nome = :nome")
        public List<Categoria> buscarCategoriaPorNome(String nome) {
            return repository.findByNomeContaining(nome);
        }

        public List<VendasPorCategoriaDTO> vendasPorCategoria() {
            return repository.vendasPorCategoria();
        }

    }
}
