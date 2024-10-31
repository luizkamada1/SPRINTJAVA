package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.OrcaReparo;
import br.com.fiap.dao.OrcaReparoDao;

public class OrcaReparoBo {

    private OrcaReparoDao orcaReparoDao;

    public OrcaReparoBo(Connection connection) {
        this.orcaReparoDao = new OrcaReparoDao(connection);
    }

    // Método para adicionar um orçamento de reparo com validações de regras de negócio
    public void adicionarOrcamento(OrcaReparo orcaReparo) throws SQLException {
        if (orcaReparo == null) {
            throw new IllegalArgumentException("O orçamento de reparo não pode ser nulo.");
        }
        if (orcaReparo.getIdOrca() <= 0) {
            throw new IllegalArgumentException("O ID do orçamento de reparo é obrigatório e deve ser positivo.");
        }
        if (orcaReparo.getPrecoEstim() < 0) {
            throw new IllegalArgumentException("O preço estimado não pode ser negativo.");
        }
        if (orcaReparo.getDiagVeiculoIdDiagnostico() <= 0) {
            throw new IllegalArgumentException("O ID do diagnóstico do veículo é obrigatório.");
        }
        // Verificar se o orçamento de reparo já existe
        if (buscarOrcamentoPorId(orcaReparo.getIdOrca()) != null) {
            throw new IllegalArgumentException("Orçamento de reparo com este ID já está cadastrado.");
        }
        orcaReparoDao.inserir(orcaReparo);
    }

    // Método para atualizar um orçamento de reparo com validações de regras de negócio
    public void atualizarOrcamento(OrcaReparo orcaReparo) throws SQLException {
        if (orcaReparo == null) {
            throw new IllegalArgumentException("O orçamento de reparo não pode ser nulo.");
        }
        if (orcaReparo.getIdOrca() <= 0) {
            throw new IllegalArgumentException("O ID do orçamento de reparo é obrigatório e deve ser positivo.");
        }
        // Verificar se o orçamento de reparo existe antes de atualizar
        if (buscarOrcamentoPorId(orcaReparo.getIdOrca()) == null) {
            throw new IllegalArgumentException("Orçamento de reparo com este ID não encontrado.");
        }
        orcaReparoDao.atualizar(orcaReparo);
    }

    // Método para deletar um orçamento de reparo pelo ID
    public void deletarOrcamento(int idOrca) throws SQLException {
        if (idOrca <= 0) {
            throw new IllegalArgumentException("O ID do orçamento de reparo é obrigatório e deve ser positivo.");
        }
        // Verificar se o orçamento de reparo existe antes de deletar
        if (buscarOrcamentoPorId(idOrca) == null) {
            throw new IllegalArgumentException("Orçamento de reparo com este ID não encontrado.");
        }
        orcaReparoDao.deletar(idOrca);
    }

    // Método para buscar um orçamento de reparo pelo ID
    public OrcaReparo buscarOrcamentoPorId(int idOrca) throws SQLException {
        if (idOrca <= 0) {
            throw new IllegalArgumentException("O ID do orçamento de reparo é obrigatório e deve ser positivo.");
        }
        return orcaReparoDao.buscarPorId(idOrca);
    }

    // Método para listar todos os orçamentos de reparo
    public List<OrcaReparo> listarTodosOrcamentos() throws SQLException {
        return orcaReparoDao.listarTodos();
    }
}
