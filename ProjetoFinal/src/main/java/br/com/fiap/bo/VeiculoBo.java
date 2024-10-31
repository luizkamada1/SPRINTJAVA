package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Veiculo;
import br.com.fiap.dao.VeiculoDao;

public class VeiculoBo {

    private VeiculoDao veiculoDao;

    public VeiculoBo(Connection connection) {
        this.veiculoDao = new VeiculoDao(connection);
    }

    // Método para adicionar um veículo, com validações de regras de negócio
    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("A placa do veículo é obrigatória.");
        }
        if (veiculo.getClienteCpf() == null || veiculo.getClienteCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        // Verificar se o veículo já existe pela placa
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Veículo com esta placa já está cadastrado.");
        }
        veiculoDao.inserir(veiculo);
    }

    // Método para atualizar um veículo, com validações de regras de negócio
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("A placa do veículo é obrigatória.");
        }
        // Verificar se o veículo existe antes de atualizar
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()) == null) {
            throw new IllegalArgumentException("Veículo com esta placa não encontrado.");
        }
        veiculoDao.atualizar(veiculo);
    }

    // Método para deletar um veículo pela placa
    public void deletarVeiculo(String placa) throws SQLException {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória para deletar um veículo.");
        }
        // Verificar se o veículo existe antes de deletar
        if (buscarVeiculoPorPlaca(placa) == null) {
            throw new IllegalArgumentException("Veículo com esta placa não encontrado.");
        }
        veiculoDao.deletar(placa);
    }

    // Método para buscar um veículo pela placa
    public Veiculo buscarVeiculoPorPlaca(String placa) throws SQLException {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória para buscar um veículo.");
        }
        return veiculoDao.buscarPorPlaca(placa);
    }

    // Método para listar todos os veículos
    public List<Veiculo> listarTodosVeiculos() throws SQLException {
        return veiculoDao.listarTodos();
    }

    // Método para listar todos os veículos de um cliente específico (por CPF)
    public List<Veiculo> listarVeiculosPorCliente(String clienteCpf) throws SQLException {
        if (clienteCpf == null || clienteCpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório para listar veículos.");
        }
        return veiculoDao.listarVeiculosPorCliente(clienteCpf);
    }
}
