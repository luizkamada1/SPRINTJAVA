package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.CentroAuto;
import br.com.fiap.bo.CentroAutoBo;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteCentroAuto {

    public static void main(String[] args) {
        ConexaoFactory conexaoFactory = new ConexaoFactory();

        try (Connection connection = conexaoFactory.conexao()) {
            CentroAutoBo centroAutoBo = new CentroAutoBo(connection);

            // Adicionando um centro automotivo
            CentroAuto centroAuto = new CentroAuto(1, "Auto Center São Paulo", "Av. Paulista, 1000", "01310-000");
            centroAutoBo.adicionarCentro(centroAuto);
            System.out.println("Centro automotivo adicionado: Nome - " + centroAuto.getNome());

            // Atualizando um centro automotivo
            centroAuto.setNome("Auto Center São Paulo Atualizado");
            centroAuto.setEndereco("Av. Paulista, 1100");
            centroAutoBo.atualizarCentro(centroAuto);
            System.out.println("Centro automotivo atualizado: Nome - " + centroAuto.getNome());

            // Buscando um centro automotivo pelo ID
            CentroAuto centroBuscado = centroAutoBo.buscarCentroPorId(1);
            if (centroBuscado != null) {
                System.out.println("Centro automotivo encontrado: Nome - " + centroBuscado.getNome());
            } else {
                System.out.println("Centro automotivo não encontrado.");
            }

            // Listando todos os centros automotivos
            List<CentroAuto> centros = centroAutoBo.listarTodosCentros();
            System.out.println("Lista de centros automotivos:");
            for (CentroAuto c : centros) {
                System.out.println("ID: " + c.getIdCentro() + ", Nome: " + c.getNome() + ", Endereço: " + c.getEndereco());
            }

            // Deletando um centro automotivo pelo ID
            centroAutoBo.deletarCentro(1);
            System.out.println("Centro automotivo deletado.");

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}
