package br.com.alura.comex_nova_versao.service;

import br.com.alura.comex_nova_versao.model.Cliente;
import br.com.alura.comex_nova_versao.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public void cadastrar(Cliente novoCliente) {

        if (novoCliente == null) return;

        repository.save(novoCliente);

    }

    public Cliente buscarClientePorId(Long clienteId) {

            return repository.findById(clienteId).get();

    }
}
