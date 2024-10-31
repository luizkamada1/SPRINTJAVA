package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.CentroAuto;
import br.com.fiap.dao.CentroAutoDao;

public class CentroAutoBo {

    private CentroAutoDao centroAutoDao;

    public CentroAutoBo(Connection connection) {
        this.centroAutoDao = new CentroAutoDao(connection);
    }

    // Método para adicionar um centro automotivo com validações de regras de negócio
    public void adicionarCentro(CentroAuto centroAuto) throws SQLException {
        if (centroAuto == null) {
            throw new IllegalArgumentException("O centro automotivo não pode ser nulo.");
        }
        if (centroAuto.getIdCentro() <= 0) {
            throw new IllegalArgumentException("O ID do centro automotivo é obrigatório e deve ser positivo.");
        }
        if (centroAuto.getNome() == null || centroAuto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do centro automotivo é obrigatório.");
        }
        if (centroAuto.getEndereco() == null || centroAuto.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O endereço do centro automotivo é obrigatório.");
        }
        if (centroAuto.getCep() == null || centroAuto.getCep().isEmpty()) {
            throw new IllegalArgumentException("O CEP do centro automotivo é obrigatório.");
        }
        // Verificar se o centro automotivo já existe pelo ID
        if (buscarCentroPorId(centroAuto.getIdCentro()) != null) {
            throw new IllegalArgumentException("Centro automotivo com este ID já está cadastrado.");
        }
        centroAutoDao.inserir(centroAuto);
    }

    // Método para atualizar um centro automotivo com validações de regras de negócio
    public void atualizarCentro(CentroAuto centroAuto) throws SQLException {
        if (centroAuto == null) {
            throw new IllegalArgumentException("O centro automotivo não pode ser nulo.");
        }
        if (centroAuto.getIdCentro() <= 0) {
            throw new IllegalArgumentException("O ID do centro automotivo é obrigatório e deve ser positivo.");
        }
        // Verificar se o centro automotivo existe antes de atualizar
        if (buscarCentroPorId(centroAuto.getIdCentro()) == null) {
            throw new IllegalArgumentException("Centro automotivo com este ID não encontrado.");
        }
        centroAutoDao.atualizar(centroAuto);
    }

    // Método para deletar um centro automotivo pelo ID
    public void deletarCentro(int idCentro) throws SQLException {
        if (idCentro <= 0) {
            throw new IllegalArgumentException("O ID do centro automotivo é obrigatório e deve ser positivo.");
        }
        // Verificar se o centro automotivo existe antes de deletar
        if (buscarCentroPorId(idCentro) == null) {
            throw new IllegalArgumentException("Centro automotivo com este ID não encontrado.");
        }
        centroAutoDao.deletar(idCentro);
    }

    // Método para buscar um centro automotivo pelo ID
    public CentroAuto buscarCentroPorId(int idCentro) throws SQLException {
        if (idCentro <= 0) {
            throw new IllegalArgumentException("O ID do centro automotivo é obrigatório e deve ser positivo.");
        }
        return centroAutoDao.buscarPorId(idCentro);
    }

    // Método para listar todos os centros automotivos
    public List<CentroAuto> listarTodosCentros() throws SQLException {
        return centroAutoDao.listarTodos();
    }
}
