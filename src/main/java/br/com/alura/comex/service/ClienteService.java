package br.com.alura.comex.service;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository repository = null;

    @Autowired
    public ClienteService() {
    }

    public void cadastrar(Cliente novoCliente) {

        if (novoCliente == null) return;

        repository.save(novoCliente);

    }

    public List<Cliente> buscarTodos() {
        Iterable<Cliente> categorias = repository.findAll();
        List<Cliente> listaClientes = new ArrayList<>();
        categorias.forEach(listaClientes::add);
        return listaClientes;
    }

    public Cliente buscarClientePorId(Long clienteId) {

        return repository.findById(clienteId).get();

    }

    public void listarClientesFieis() {
        List<Object[]> resultados = repository.listarClientesFieis();

        System.out.println("---------------------------------------------");
        System.out.println("Relatório de clientes fiéis");
        System.out.println("---------------------------------------------");

        for (Object[] resultado : resultados) {
            String nomeCliente = (String) resultado[0];
            Long numeroDePedidos = (Long) resultado[1];
            BigDecimal valorComprado = (BigDecimal) resultado[2];

            System.out.println("Cliente: " + nomeCliente);
            System.out.println("Número de pedidos: " + numeroDePedidos);
            System.out.println("Valor comprado: " + valorComprado);
            System.out.println("---------------------------------------------");
        }

    }
}
