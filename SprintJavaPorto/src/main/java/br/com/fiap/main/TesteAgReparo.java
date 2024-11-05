package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import br.com.fiap.beans.AgReparo;
import br.com.fiap.bo.AgReparoBo;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteAgReparo {

    public static void main(String[] args) {
        ConexaoFactory conexaoFactory = new ConexaoFactory();

        try (Connection connection = conexaoFactory.conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);

            // Adicionando um agendamento de reparo
            AgReparo agReparo = new AgReparo(1, new Date(), new String(), 123456789, 1);
            agReparoBo.adicionarAgendamento(agReparo);
            System.out.println("Agendamento de reparo adicionado: ID - " + agReparo.getIdReparo());

            // Atualizando um agendamento de reparo
            agReparo.setClienteCpf(987654321);
            agReparoBo.atualizarAgendamento(agReparo);
            System.out.println("Agendamento de reparo atualizado: ID - " + agReparo.getIdReparo());

            // Buscando um agendamento de reparo pelo ID
            AgReparo agendamentoBuscado = agReparoBo.buscarAgendamentoPorId(1);
            if (agendamentoBuscado != null) {
                System.out.println("Agendamento encontrado: Data - " + agendamentoBuscado.getDataAg());
            } else {
                System.out.println("Agendamento de reparo não encontrado.");
            }

            // Listando todos os agendamentos de reparo
            List<AgReparo> agendamentos = agReparoBo.listarTodosAgendamentos();
            System.out.println("Lista de agendamentos de reparo:");
            for (AgReparo a : agendamentos) {
                System.out.println("ID: " + a.getIdReparo() + ", Data: " + a.getDataAg() + ", CPF Cliente: " + a.getClienteCpf());
            }

            // Listando todos os agendamentos de reparo de um cliente específico
            List<AgReparo> agendamentosCliente = agReparoBo.listarAgendamentosPorCliente(123456789);
            System.out.println("Agendamentos de reparo para o cliente com CPF 123456789:");
            for (AgReparo a : agendamentosCliente) {
                System.out.println("ID: " + a.getIdReparo() + ", Data: " + a.getDataAg());
            }

            // Deletando um agendamento de reparo pelo ID
            agReparoBo.deletarAgendamento(1);
            System.out.println("Agendamento de reparo deletado.");

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}
