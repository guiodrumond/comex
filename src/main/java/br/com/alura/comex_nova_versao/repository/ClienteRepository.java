package br.com.alura.comex_nova_versao.repository;

import br.com.alura.comex_nova_versao.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
