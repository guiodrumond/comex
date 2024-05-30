package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDao {

    public void cadastra(Cliente novoCliente) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(novoCliente);
        entityManager.getTransaction().commit();

    }

    public void atualiza(Cliente clienteAtualizado) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(clienteAtualizado);
        entityManager.getTransaction().commit();

    }

    public List<Cliente> listaPorNome(String nome) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class);
        query.setParameter("nome", nome);
        return query.getResultList();

    }

    public List<Cliente> listaTodos() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();

    }

    public Cliente buscaPorId(Long id) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        Cliente clienteBuscado = entityManager.find(Cliente.class, id);
        return clienteBuscado;

    }

    public void remove(Cliente cliente) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Cliente clienteParaRemover = entityManager.find(Cliente.class, cliente.getId());
        if (clienteParaRemover != null) {
            entityManager.remove(clienteParaRemover);
        }
        entityManager.getTransaction().commit();

    }


}
