package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
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


    public void listarClientesFieis() {

        EntityManager entityManager = JPAUtil.getEntityManager();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT c.nome, COUNT(p), SUM(i.precoUnitario * i.quantidade) " +
                        "FROM Cliente c " +
                        "JOIN Pedido p ON c = p.cliente " +
                        "JOIN ItemDePedido i ON p = i.pedido " +
                        "GROUP BY c.nome " +
                        "ORDER BY SUM(i.precoUnitario * i.quantidade) DESC", Object[].class)
                .setMaxResults(3);;

        List<Object[]> resultados = query.getResultList();

        System.out.println("---------------------------------------------");
        System.out.println("Relatório de clientes fiéis");
        System.out.println("---------------------------------------------");

        for (Object[] resultado : resultados) {
            String nomeCliente = (String) resultado[0];
            Long numeroDePedidos = (Long) resultado[1];
            BigDecimal valorComprado = (BigDecimal) resultado[2];

            System.out.println("Categoria: " + nomeCliente);
            System.out.println("Número de pedidos: " + numeroDePedidos);
            System.out.println("Valor comprado: " + valorComprado);
            System.out.println("---------------------------------------------");
        }

    }
}
