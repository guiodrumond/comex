package br.com.alura.comex_nova_versao.repository;

import br.com.alura.comex_nova_versao.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
