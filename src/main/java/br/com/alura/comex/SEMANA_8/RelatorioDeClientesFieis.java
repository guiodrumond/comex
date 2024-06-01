package br.com.alura.comex.SEMANA_8;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.model.Cliente;

public class RelatorioDeClientesFieis {

    public static void main(String[] args) {

        ClienteDao clienteDao = new ClienteDao();

        clienteDao.listarClientesFieis();

    }

}
