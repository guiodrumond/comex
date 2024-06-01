package br.com.alura.comex.dao;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.model.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
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

    public class VendasPorCategoriaDTO {
        private String nomeCategoria;
        private Long quantidadeProdutos;
        private BigDecimal totalVendas;

        public String getNomeCategoria() {
            return nomeCategoria;
        }

        public void setNomeCategoria(String nomeCategoria) {
            this.nomeCategoria = nomeCategoria;
        }

        public Long getQuantidadeProdutos() {
            return quantidadeProdutos;
        }

        public void setQuantidadeProdutos(Long quantidadeProdutos) {
            this.quantidadeProdutos = quantidadeProdutos;
        }

        public BigDecimal getTotalVendas() {
            return totalVendas;
        }

        public void setTotalVendas(BigDecimal totalVendas) {
            this.totalVendas = totalVendas;
        }
    }



    public List<VendasPorCategoriaDTO> vendasPorCategoria() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<VendasPorCategoriaDTO> query = entityManager.createQuery(
                "SELECT new br.com.alura.comex.relatorios.VendasPorCategoriaDTO(c.nome, COUNT(p), SUM(p.precoUnitario)) " +
                        "FROM Categoria c JOIN c.produtos p GROUP BY c.nome", VendasPorCategoriaDTO.class);

        return query.getResultList();
    }



}
