package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.AgReparo;
import br.com.fiap.dao.AgReparoDao;

public class AgReparoBo {

    private AgReparoDao agReparoDao;

    public AgReparoBo(Connection connection) {
        this.agReparoDao = new AgReparoDao(connection);
    }

    // Método para adicionar um agendamento de reparo com validações de regras de negócio
    public void adicionarAgendamento(AgReparo agReparo) throws SQLException {
        if (agReparo == null) {
            throw new IllegalArgumentException("O agendamento de reparo não pode ser nulo.");
        }
        if (agReparo.getIdReparo() <= 0) {
            throw new IllegalArgumentException("O ID do reparo é obrigatório e deve ser positivo.");
        }
        if (agReparo.getClienteCpf() <= 0) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (agReparo.getCentroAutoIdCentro() <= 0) {
            throw new IllegalArgumentException("O ID do centro automotivo é obrigatório.");
        }
        if (agReparo.getDataAg() == null) {
            throw new IllegalArgumentException("A data do agendamento é obrigatória.");
        }
        if (agReparo.getHoraAg() == null) {
            throw new IllegalArgumentException("A hora do agendamento é obrigatória.");
        }
        // Verificar se o agendamento de reparo já existe pelo ID
        if (buscarAgendamentoPorId(agReparo.getIdReparo()) != null) {
            throw new IllegalArgumentException("Agendamento de reparo com este ID já está cadastrado.");
        }
        agReparoDao.inserir(agReparo);
    }

    // Método para atualizar um agendamento de reparo com validações de regras de negócio
    public void atualizarAgendamento(AgReparo agReparo) throws SQLException {
        if (agReparo == null) {
            throw new IllegalArgumentException("O agendamento de reparo não pode ser nulo.");
        }
        if (agReparo.getIdReparo() <= 0) {
            throw new IllegalArgumentException("O ID do reparo é obrigatório e deve ser positivo.");
        }
        // Verificar se o agendamento de reparo existe antes de atualizar
        if (buscarAgendamentoPorId(agReparo.getIdReparo()) == null) {
            throw new IllegalArgumentException("Agendamento de reparo com este ID não encontrado.");
        }
        agReparoDao.atualizar(agReparo);
    }

    // Método para deletar um agendamento de reparo pelo ID
    public void deletarAgendamento(int idReparo) throws SQLException {
        if (idReparo <= 0) {
            throw new IllegalArgumentException("O ID do reparo é obrigatório e deve ser positivo.");
        }
        // Verificar se o agendamento de reparo existe antes de deletar
        if (buscarAgendamentoPorId(idReparo) == null) {
            throw new IllegalArgumentException("Agendamento de reparo com este ID não encontrado.");
        }
        agReparoDao.deletar(idReparo);
    }

    // Método para buscar um agendamento de reparo pelo ID
    public AgReparo buscarAgendamentoPorId(int idReparo) throws SQLException {
        if (idReparo <= 0) {
            throw new IllegalArgumentException("O ID do reparo é obrigatório e deve ser positivo.");
        }
        return agReparoDao.buscarPorId(idReparo);
    }

    // Método para listar todos os agendamentos de reparo
    public List<AgReparo> listarTodosAgendamentos() throws SQLException {
        return agReparoDao.listarTodos();
    }

    // Método para listar todos os agendamentos de reparo de um cliente específico (por CPF)
    public List<AgReparo> listarAgendamentosPorCliente(int clienteCpf) throws SQLException {
        if (clienteCpf <= 0) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório para listar agendamentos.");
        }
        return agReparoDao.listarReparosPorCliente(clienteCpf);
    }
}
