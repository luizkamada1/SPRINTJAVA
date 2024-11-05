package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Cliente;
import br.com.fiap.bo.ClienteBo;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteCliente {

    public static void main(String[] args) {
        ConexaoFactory conexaoFactory = new ConexaoFactory();

        try (Connection connection = conexaoFactory.conexao()) {
            ClienteBo clienteBo = new ClienteBo(connection);

            // Adicionando um cliente
            Cliente cliente = new Cliente("12345678900", "João Silva", "11987654321", "joao@example.com", "senha123");
            clienteBo.adicionarCliente(cliente);
            System.out.println("Cliente adicionado: " + cliente.getNome());

            // Atualizando um cliente
            cliente.setNome("João da Silva");
            cliente.setTelefone("11999998888");
            clienteBo.atualizarCliente(cliente);
            System.out.println("Cliente atualizado: " + cliente.getNome());

            // Buscando um cliente pelo CPF
            Cliente clienteBuscado = clienteBo.buscarClientePorCpf("12345678900");
            if (clienteBuscado != null) {
                System.out.println("Cliente encontrado: " + clienteBuscado.getNome());
            } else {
                System.out.println("Cliente não encontrado.");
            }

            // Listando todos os clientes
            List<Cliente> clientes = clienteBo.listarTodosClientes();
            System.out.println("Lista de clientes:");
            for (Cliente c : clientes) {
                System.out.println("Nome: " + c.getNome() + ", CPF: " + c.getCpf());
            }

            // Deletando um cliente
            clienteBo.deletarCliente("12345678900");
            System.out.println("Cliente deletado.");

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}
