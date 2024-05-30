package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class PedidoDao {

    public Pedido buscaPorId(Long id) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        return entityManager.find(Pedido.class, id);

    }

    public void cadastra(Pedido novoPedido) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(novoPedido);
        entityManager.getTransaction().commit();

    }

    public List<Pedido> buscaPorData(LocalDate data) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.data = :data", Pedido.class);
        query.setParameter("data", data);

        return query.getResultList();

    }

}
