package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.DiagVeiculo;
import br.com.fiap.bo.DiagVeiculoBo;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteDiagVeiculo {

    public static void main(String[] args) {
        ConexaoFactory conexaoFactory = new ConexaoFactory();

        try (Connection connection = conexaoFactory.conexao()) {
            DiagVeiculoBo diagVeiculoBo = new DiagVeiculoBo(connection);

            // Adicionando um diagnóstico de veículo
            DiagVeiculo diagVeiculo = new DiagVeiculo(1, "Diagnóstico Completo", "Sensor de bordo", 101, "Motor falhando", 1001);
            diagVeiculoBo.adicionarDiagnostico(diagVeiculo);
            System.out.println("Diagnóstico de veículo adicionado: ID " + diagVeiculo.getIdDiag());

            // Atualizando um diagnóstico de veículo
            diagVeiculo.setNome("Diagnóstico Completo Atualizado");
            diagVeiculo.setSintomas("Motor falhando e aquecendo");
            diagVeiculoBo.atualizarDiagnostico(diagVeiculo);
            System.out.println("Diagnóstico de veículo atualizado: ID " + diagVeiculo.getIdDiag());

            // Buscando um diagnóstico de veículo pelo ID
            DiagVeiculo diagBuscado = diagVeiculoBo.buscarDiagnosticoPorId(1);
            if (diagBuscado != null) {
                System.out.println("Diagnóstico encontrado: Nome - " + diagBuscado.getNome());
            } else {
                System.out.println("Diagnóstico de veículo não encontrado.");
            }

            // Listando todos os diagnósticos de veículos
            List<DiagVeiculo> diagnosticos = diagVeiculoBo.listarTodosDiagnosticos();
            System.out.println("Lista de diagnósticos de veículos:");
            for (DiagVeiculo d : diagnosticos) {
                System.out.println("ID: " + d.getIdDiag() + ", Nome: " + d.getNome() + ", Sintomas: " + d.getSintomas());
            }

            // Deletando um diagnóstico de veículo pelo ID
            diagVeiculoBo.deletarDiagnostico(1);
            System.out.println("Diagnóstico de veículo deletado.");

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}
