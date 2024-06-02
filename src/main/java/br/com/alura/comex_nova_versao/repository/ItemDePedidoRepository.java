package br.com.alura.comex_nova_versao.repository;

import br.com.alura.comex_nova_versao.model.ItemDePedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDePedidoRepository extends CrudRepository<ItemDePedido, Long> {
}
