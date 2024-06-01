package br.com.alura.comex.testes;

import br.com.alura.comex.JPAUtil;
import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeracaoDeDadosParaTabelas {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        for (int i = 1; i <= 6; i++) {
            Cliente cliente = new Cliente();
            Endereco endereco = new Endereco();
            cliente.setNome("Cliente " + i);
            cliente.setCpf("123.456.789-0" + i);
            cliente.setEmail("cliente" + i + "@exemplo.com");
            cliente.setProfissao("Profissão " + i);
            cliente.setTelefone("(11) 9876-543" + i);

            endereco.setLogradouro("Rua do Cliente " + i);
            endereco.setNumero(100 + i);
            endereco.setBairro("Centro");
            endereco.setCidade("São Paulo");
            endereco.setEstado("SP");

            cliente.setEndereco(endereco);

            entityManager.persist(cliente);
        }

        for (int i = 1; i <= 6; i++) {
            Categoria categoria = new Categoria();
            categoria.setNome("Categoria " + i);
            categoria.setAtiva(true);

            entityManager.persist(categoria);
        }


        for (int i = 1; i <= 30; i++) {

            Random random = new Random();

            Produto produto = new Produto();
            produto.setNome("Produto " + i);
            produto.setDescricao("Descrição do Produto " + i);
            produto.setPrecoUnitario(BigDecimal.valueOf(random.nextDouble() * (1000.00 - 0.01)));
            produto.setQuantidade(random.nextInt(500) + 1);

            List<Categoria> listaDeCategorias;

            TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class);

            listaDeCategorias = query.getResultList();

            int indiceSorteado = random.nextInt(listaDeCategorias.size());
            Categoria categoriaSorteada = listaDeCategorias.get(indiceSorteado);

            produto.setCategoria(categoriaSorteada);

            entityManager.persist(produto);
        }


        Pedido pedido;
        for (int i = 1; i <= 30; i++) {

            Random random = new Random();

            pedido = new Pedido();

            List<Cliente> listaDeClientes;
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            listaDeClientes = query.getResultList();

            int indiceSorteado = random.nextInt(listaDeClientes.size());
            Cliente clienteSorteado = listaDeClientes.get(indiceSorteado);

            pedido.setCliente(clienteSorteado);
            pedido.setFidelidade(false);
            pedido.setDesconto(BigDecimal.ZERO);

            entityManager.persist(pedido);

        }

        List<Pedido> listaDePedidos;
        TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class);
        listaDePedidos = query.getResultList();

        for (int i = 0; i < listaDePedidos.size(); i++) {

            Random random = new Random();

            int numeroDeItens = random.nextInt(10) + 1;

            for (int p = 0; p < numeroDeItens; p++) {
                ItemDePedido itemDePedido = new ItemDePedido();

                itemDePedido.setPedido(listaDePedidos.get(p));

                List<Produto> listaDeProdutos;
                TypedQuery<Produto> novaQuery = entityManager.createQuery("SELECT c FROM Produto c", Produto.class);
                listaDeProdutos = novaQuery.getResultList();

                int indiceSorteado = random.nextInt(listaDeProdutos.size());
                Produto produtoSorteado = listaDeProdutos.get(indiceSorteado);

                itemDePedido.setProduto(produtoSorteado);

                itemDePedido.setQuantidade(random.nextInt(100) + 1);

                itemDePedido.setDesconto(BigDecimal.ZERO);

                itemDePedido.setTipoDesconto(TipoDescontoProdutoEnum.NENHUM);

                itemDePedido.setPrecoUnitario(itemDePedido.getProduto().getPrecoUnitario());

                entityManager.persist(itemDePedido);
            }

        }

        entityManager.getTransaction().commit();

    }




}
