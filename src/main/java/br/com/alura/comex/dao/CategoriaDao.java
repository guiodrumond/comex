package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriaDao {

    public void cadastra(Categoria novaCategoria) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(novaCategoria);
        entityManager.getTransaction().commit();

    }

    public Categoria buscaPorId(Long id) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        return entityManager.find(Categoria.class, id);

    }

    public List<Categoria> listaTodas() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class);
        return query.getResultList();
    }

    public List<Categoria> vendasPorCategoria() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class);
        return query.getResultList();
    }



}
