package br.com.alura.comex_nova_versao.repository;

import br.com.alura.comex_nova_versao.model.Categoria;
import br.com.alura.comex_nova_versao.relatorios.VendasPorCategoriaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {


}
