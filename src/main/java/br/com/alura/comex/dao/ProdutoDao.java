package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProdutoDao {

    public void cadastra(Produto novoProduto) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(novoProduto);
        entityManager.getTransaction().commit();

    }

    public Produto buscaPorId(Long id) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        return entityManager.find(Produto.class, id);

    }

    public List<Produto> listaTodos() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p", Produto.class);
        return query.getResultList();
    }

    public List<Produto> listaIndisponiveis() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Produto> query = entityManager
                .createQuery("SELECT p FROM Produto p WHERE p.quantidade = 0", Produto.class);
        return query.getResultList();
    }

}
