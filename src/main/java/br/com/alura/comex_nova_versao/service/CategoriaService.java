package br.com.alura.comex_nova_versao.service;

import br.com.alura.comex_nova_versao.model.Categoria;
import br.com.alura.comex_nova_versao.model.StatusCategoriaEnum;
import br.com.alura.comex_nova_versao.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean atualizarStatus(Long categoriaId, StatusCategoriaEnum novoStatus) {
        Categoria categoria = repository.findById(categoriaId).orElse(null);
        if (categoria == null) {
            return false;
        }
        categoria.setStatus(novoStatus);
        repository.save(categoria);
        return true;
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

}
