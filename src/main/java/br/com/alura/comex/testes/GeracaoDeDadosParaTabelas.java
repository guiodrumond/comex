package br.com.alura.comex.testes;

import br.com.alura.comex.model.*;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ClienteService;
import br.com.alura.comex.service.PedidoService;
import br.com.alura.comex.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class GeracaoDeDadosParaTabelas {

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService();
        CategoriaService categoriaService = new CategoriaService();
        ProdutoService produtoService = new ProdutoService();
        PedidoService pedidoService = new PedidoService();


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

            clienteService.cadastrar(cliente);
        }

        for (int i = 1; i <= 6; i++) {
            Categoria categoria = new Categoria();
            categoria.setNome("Categoria " + i);
            categoria.setAtiva(true);

            categoriaService.cadastrar(categoria);
        }


        for (int i = 1; i <= 30; i++) {

            Random random = new Random();

            Produto produto = new Produto();
            produto.setNome("Produto " + i);
            produto.setDescricao("Descrição do Produto " + i);
            produto.setPrecoUnitario(BigDecimal.valueOf(random.nextDouble() * (1000.00 - 0.01)));
            produto.setQuantidade(random.nextInt(500) + 1);

            List<Categoria> listaDeCategorias = categoriaService.buscarTodas();

            int indiceSorteado = random.nextInt(listaDeCategorias.size());
            Categoria categoriaSorteada = listaDeCategorias.get(indiceSorteado);

            produto.setCategoria(categoriaSorteada);

            produtoService.cadastrar(produto);
        }


        Pedido pedido;
        for (int i = 1; i <= 30; i++) {

            Random random = new Random();

            pedido = new Pedido();

            List<Cliente> listaDeClientes = clienteService.buscarTodos();

            int indiceSorteado = random.nextInt(listaDeClientes.size());
            Cliente clienteSorteado = listaDeClientes.get(indiceSorteado);

            pedido.setCliente(clienteSorteado);
            pedido.setFidelidade(false);
            pedido.setDesconto(BigDecimal.ZERO);

            pedidoService.cadastrar(pedido);

        }

        List<Pedido> listaDePedidos = pedidoService.buscarTodos();

        for (int i = 0; i < listaDePedidos.size(); i++) {

            Random random = new Random();

            int numeroDeItens = random.nextInt(10) + 1;

            for (int p = 0; p < numeroDeItens; p++) {
                ItemDePedido itemDePedido = new ItemDePedido();

                itemDePedido.setPedido(listaDePedidos.get(p));

                List<Produto> listaDeProdutos = produtoService.buscarTodos();

                int indiceSorteado = random.nextInt(listaDeProdutos.size());
                Produto produtoSorteado = listaDeProdutos.get(indiceSorteado);

                itemDePedido.setProduto(produtoSorteado);

                itemDePedido.setQuantidade(random.nextInt(100) + 1);

                itemDePedido.setDesconto(BigDecimal.ZERO);

                itemDePedido.setTipoDesconto(TipoDescontoProdutoEnum.NENHUM);

                itemDePedido.setPrecoUnitario(itemDePedido.getProduto().getPrecoUnitario());

                pedidoService.cadastrarItemDePedido(itemDePedido);
            }

        }

    }

}
