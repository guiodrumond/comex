package br.com.alura.comex_nova_versao.repository;

import br.com.alura.comex_nova_versao.model.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    int countByClienteId(Long id);
}
