package br.com.fiap.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.OrcaReparo;
import br.com.fiap.bo.OrcaReparoBo;
import br.com.fiap.conexoes.ConexaoFactory;

public class TesteOrcaReparo {

    public static void main(String[] args) {
        ConexaoFactory conexaoFactory = new ConexaoFactory();

        try (Connection connection = conexaoFactory.conexao()) {
            OrcaReparoBo orcaReparoBo = new OrcaReparoBo(connection);

            // Adicionando um orçamento de reparo
            OrcaReparo orcaReparo = new OrcaReparo(1, 1500.00, "Substituição da embreagem", "Troca do radiador", "Correia dentada", 1001);
            orcaReparoBo.adicionarOrcamento(orcaReparo);
            System.out.println("Orçamento de reparo adicionado: ID " + orcaReparo.getIdOrca());

            // Atualizando um orçamento de reparo
            orcaReparo.setPrecoEstim(1600.00);
            orcaReparo.setReparoRad("Radiador atualizado");
            orcaReparoBo.atualizarOrcamento(orcaReparo);
            System.out.println("Orçamento de reparo atualizado: ID " + orcaReparo.getIdOrca());

            // Buscando um orçamento de reparo pelo ID
            OrcaReparo orcamentoBuscado = orcaReparoBo.buscarOrcamentoPorId(1);
            if (orcamentoBuscado != null) {
                System.out.println("Orçamento de reparo encontrado: Preço estimado - " + orcamentoBuscado.getPrecoEstim());
            } else {
                System.out.println("Orçamento de reparo não encontrado.");
            }

            // Listando todos os orçamentos de reparo
            List<OrcaReparo> orcamentos = orcaReparoBo.listarTodosOrcamentos();
            System.out.println("Lista de orçamentos de reparo:");
            for (OrcaReparo o : orcamentos) {
                System.out.println("ID: " + o.getIdOrca() + ", Preço estimado: " + o.getPrecoEstim() + ", Reparo Radiador: " + o.getReparoRad());
            }

            // Deletando um orçamento de reparo pelo ID
            orcaReparoBo.deletarOrcamento(1);
            System.out.println("Orçamento de reparo deletado.");

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}
