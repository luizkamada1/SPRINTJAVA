package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDao;

public class ClienteBo {

    private ClienteDao clienteDao;

    public ClienteBo(Connection connection) {
        this.clienteDao = new ClienteDao(connection);
    }

    // Método para adicionar um cliente, com validações de regras de negócio
    public void adicionarCliente(Cliente cliente) throws SQLException {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        // Verificar se o cliente já existe pelo CPF
        if (buscarClientePorCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException("Cliente com este CPF já está cadastrado.");
        }
        clienteDao.inserir(cliente);
    }

    // Método para atualizar um cliente, com validações de regras de negócio
    public void atualizarCliente(Cliente cliente) throws SQLException {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        // Verificar se o cliente existe antes de atualizar
        if (buscarClientePorCpf(cliente.getCpf()) == null) {
            throw new IllegalArgumentException("Cliente com este CPF não encontrado.");
        }
        clienteDao.atualizar(cliente);
    }

    // Método para deletar um cliente pelo CPF
    public void deletarCliente(String cpf) throws SQLException {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório para deletar um cliente.");
        }
        // Verificar se o cliente existe antes de deletar
        if (buscarClientePorCpf(cpf) == null) {
            throw new IllegalArgumentException("Cliente com este CPF não encontrado.");
        }
        clienteDao.deletar(cpf);
    }

    // Método para buscar um cliente pelo CPF
    public Cliente buscarClientePorCpf(String cpf) throws SQLException {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório para buscar um cliente.");
        }
        return clienteDao.buscarPorCpf(cpf);
    }

    // Método para listar todos os clientes
    public List<Cliente> listarTodosClientes() throws SQLException {
        return clienteDao.listarTodos();
    }
}
