package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ClienteDao;

public class TesteClienteRemover {

    public static void main(String[] args) {

        ClienteDao clienteDAO = new ClienteDao();

        clienteDAO.remove(clienteDAO.buscaPorId(22l));

    }

}
