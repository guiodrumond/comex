package br.com.alura.comex.testes;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;

public class TesteClienteCadastrar {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Pedro Andrade Souza", "88899955555", "Pedro@gmail.com");
        Cliente cliente2 = new Cliente("Maria de Souza Cruz", "44455566622", "mariamaria@gmail.com");
        Cliente cliente3 = new Cliente("Samuel Samuca Sá", "11155599944", "sasa@gmail.com");

        Endereco enderecoDefault = new Endereco(".....",".....","..","......",0);

        cliente1.setEndereco(enderecoDefault);
        cliente2.setEndereco(enderecoDefault);
        cliente3.setEndereco(enderecoDefault);

        ClienteDao clienteDAO = new ClienteDao();

        clienteDAO.cadastra(cliente1);
        clienteDAO.cadastra(cliente2);
        clienteDAO.cadastra(cliente3);

        System.out.println(clienteDAO.listaTodos());

    }

}
