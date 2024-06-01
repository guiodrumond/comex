package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public List<Pedido> buscaPorData(String data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataFormatada = LocalDate.parse(data, formatter);

        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.data = :data", Pedido.class);
        query.setParameter("data", dataFormatada);

        return query.getResultList();

    }

    public void cadastraItemDePedido(ItemDePedido novoItemDePedido) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(novoItemDePedido);
        entityManager.getTransaction().commit();

    }

    public void adicionarItemAoPedido(ItemDePedido item) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        Pedido pedido = entityManager.find(Pedido.class, item.getPedido().getId());

        pedido.getItensDePedidos().add(item);

    }


    public void totalDeVendasPorCategoria() {

        EntityManager entityManager = JPAUtil.getEntityManager();

        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT c.nome, SUM(p.precoUnitario * i.quantidade), SUM(i.quantidade) " +
                        "FROM Categoria c " +
                        "JOIN Produto p ON c = p.categoria " +
                        "JOIN ItemDePedido i ON p = i.produto " +
                        "GROUP BY c.nome", Object[].class);

        List<Object[]> resultados = query.getResultList();

        System.out.println("---------------------------------------------");
        System.out.println("Relatório de vendas por categoria");
        System.out.println("---------------------------------------------");

        for (Object[] resultado : resultados) {
            String nomeCategoria = (String) resultado[0];
            BigDecimal valorVendido = (BigDecimal) resultado[1];
            Long quantidadeVendida = (Long) resultado[2];

            System.out.println("Categoria: " + nomeCategoria);
            System.out.println("Quantidade vendida: " + quantidadeVendida);
            System.out.println("Valor Vendido: " + valorVendido);
            System.out.println("---------------------------------------------");
        }
    }

    public List<Pedido> listaTodos() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
        return query.getResultList();
    }
}
