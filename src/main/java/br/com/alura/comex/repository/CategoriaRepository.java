package br.com.alura.comex.repository;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByNomeContaining(String nome);

    @Query("SELECT new br.com.alura.comex.relatorios.VendasPorCategoriaDTO(c.nome, COUNT(p), SUM(p.precoUnitario)) " +
            "FROM Categoria c JOIN c.produtos p GROUP BY c.nome")
    List<CategoriaService.VendasPorCategoriaDTO> vendasPorCategoria();

}
