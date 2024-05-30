package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ClienteDao;

public class TesteClienteListaPorNome {

    public static void main(String[] args) {

        ClienteDao clienteDAO = new ClienteDao();

        System.out.println(clienteDAO.listaPorNome("Samuel Samuca Sá"));

    }

}
