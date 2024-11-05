package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Veiculo;
import br.com.fiap.bo.VeiculoBo;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteVeiculo {

    public static void main(String[] args) {
        ConexaoFactory conexaoFactory = new ConexaoFactory();

        try (Connection connection = conexaoFactory.conexao()) {
            VeiculoBo veiculoBo = new VeiculoBo(connection);

            // Adicionando um veículo
            Veiculo veiculo = new Veiculo("ABC1234", "Toyota", "Corolla", 2020, "12345678900");
            veiculoBo.adicionarVeiculo(veiculo);
            System.out.println("Veículo adicionado: " + veiculo.getPlaca());

            // Atualizando um veículo
            veiculo.setMarca("Toyota Atualizado");
            veiculo.setModelo("Corolla Atualizado");
            veiculoBo.atualizarVeiculo(veiculo);
            System.out.println("Veículo atualizado: " + veiculo.getPlaca());

            // Buscando um veículo pela placa
            Veiculo veiculoBuscado = veiculoBo.buscarVeiculoPorPlaca("ABC1234");
            if (veiculoBuscado != null) {
                System.out.println("Veículo encontrado: " + veiculoBuscado.getModelo());
            } else {
                System.out.println("Veículo não encontrado.");
            }

            // Listando todos os veículos
            List<Veiculo> veiculos = veiculoBo.listarTodosVeiculos();
            System.out.println("Lista de veículos:");
            for (Veiculo v : veiculos) {
                System.out.println("Placa: " + v.getPlaca() + ", Marca: " + v.getMarca() + ", Modelo: " + v.getModelo());
            }

            // Listando todos os veículos de um cliente específico
            List<Veiculo> veiculosCliente = veiculoBo.listarVeiculosPorCliente("12345678900");
            System.out.println("Veículos do cliente com CPF 12345678900:");
            for (Veiculo v : veiculosCliente) {
                System.out.println("Placa: " + v.getPlaca() + ", Marca: " + v.getMarca() + ", Modelo: " + v.getModelo());
            }

            // Deletando um veículo pela placa
            veiculoBo.deletarVeiculo("ABC1234");
            System.out.println("Veículo deletado.");

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}
