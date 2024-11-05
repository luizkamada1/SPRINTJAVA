package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.DiagVeiculo;
import br.com.fiap.dao.DiagVeiculoDao;

public class DiagVeiculoBo {

    private DiagVeiculoDao diagVeiculoDao;

    public DiagVeiculoBo(Connection connection) {
        this.diagVeiculoDao = new DiagVeiculoDao(connection);
    }

    // Método para adicionar um diagnóstico de veículo com validações de regras de negócio
    public void adicionarDiagnostico(DiagVeiculo diagVeiculo) throws SQLException {
        if (diagVeiculo == null) {
            throw new IllegalArgumentException("O diagnóstico do veículo não pode ser nulo.");
        }
        if (diagVeiculo.getIdDiag() <= 0) {
            throw new IllegalArgumentException("O ID do diagnóstico é obrigatório e deve ser positivo.");
        }
        if (diagVeiculo.getNome() == null || diagVeiculo.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do diagnóstico é obrigatório.");
        }
        if (diagVeiculo.getVeiculoPlaca() <= 0) {
            throw new IllegalArgumentException("A placa do veículo é obrigatória.");
        }
        // Verificar se o diagnóstico já existe pelo ID
        if (buscarDiagnosticoPorId(diagVeiculo.getIdDiag()) != null) {
            throw new IllegalArgumentException("Diagnóstico com este ID já está cadastrado.");
        }
        diagVeiculoDao.inserir(diagVeiculo);
    }

    // Método para atualizar um diagnóstico de veículo com validações de regras de negócio
    public void atualizarDiagnostico(DiagVeiculo diagVeiculo) throws SQLException {
        if (diagVeiculo == null) {
            throw new IllegalArgumentException("O diagnóstico do veículo não pode ser nulo.");
        }
        if (diagVeiculo.getIdDiag() <= 0) {
            throw new IllegalArgumentException("O ID do diagnóstico é obrigatório e deve ser positivo.");
        }
        // Verificar se o diagnóstico existe antes de atualizar
        if (buscarDiagnosticoPorId(diagVeiculo.getIdDiag()) == null) {
            throw new IllegalArgumentException("Diagnóstico com este ID não encontrado.");
        }
        diagVeiculoDao.atualizar(diagVeiculo);
    }

    // Método para deletar um diagnóstico de veículo pelo ID
    public void deletarDiagnostico(int idDiag) throws SQLException {
        if (idDiag <= 0) {
            throw new IllegalArgumentException("O ID do diagnóstico é obrigatório e deve ser positivo.");
        }
        // Verificar se o diagnóstico existe antes de deletar
        if (buscarDiagnosticoPorId(idDiag) == null) {
            throw new IllegalArgumentException("Diagnóstico com este ID não encontrado.");
        }
        diagVeiculoDao.deletar(idDiag);
    }

    // Método para buscar um diagnóstico de veículo pelo ID
    public DiagVeiculo buscarDiagnosticoPorId(int idDiag) throws SQLException {
        if (idDiag <= 0) {
            throw new IllegalArgumentException("O ID do diagnóstico é obrigatório e deve ser positivo.");
        }
        return diagVeiculoDao.buscarPorId(idDiag);
    }

    // Método para listar todos os diagnósticos de veículos
    public List<DiagVeiculo> listarTodosDiagnosticos() throws SQLException {
        return diagVeiculoDao.listarTodos();
    }
}
