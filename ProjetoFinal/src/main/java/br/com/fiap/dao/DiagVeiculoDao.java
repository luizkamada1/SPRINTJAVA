package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.beans.DiagVeiculo;

public class DiagVeiculoDao {

    private Connection connection;

    public DiagVeiculoDao(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um diagnóstico de veículo
    public void inserir(DiagVeiculo diagVeiculo) throws SQLException {
        String sql = "INSERT INTO DIAG_VEICULO (id_diag, nome, comp_bordo, codigo_falha, sintomas, veiculo_placa) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, diagVeiculo.getIdDiag());
            stmt.setString(2, diagVeiculo.getNome());
            stmt.setString(3, diagVeiculo.getCompBordo());
            stmt.setInt(4, diagVeiculo.getCodigoFalha());
            stmt.setString(5, diagVeiculo.getSintomas());
            stmt.setInt(6, diagVeiculo.getVeiculoPlaca());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um diagnóstico de veículo
    public void atualizar(DiagVeiculo diagVeiculo) throws SQLException {
        String sql = "UPDATE DIAG_VEICULO SET nome=?, comp_bordo=?, codigo_falha=?, sintomas=?, veiculo_placa=? WHERE id_diag=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, diagVeiculo.getNome());
            stmt.setString(2, diagVeiculo.getCompBordo());
            stmt.setInt(3, diagVeiculo.getCodigoFalha());
            stmt.setString(4, diagVeiculo.getSintomas());
            stmt.setInt(5, diagVeiculo.getVeiculoPlaca());
            stmt.setInt(6, diagVeiculo.getIdDiag());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um diagnóstico de veículo pelo ID
    public void deletar(int idDiag) throws SQLException {
        String sql = "DELETE FROM DIAG_VEICULO WHERE id_diag=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDiag);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um diagnóstico de veículo pelo ID
    public DiagVeiculo buscarPorId(int idDiag) throws SQLException {
        String sql = "SELECT * FROM DIAG_VEICULO WHERE id_diag=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDiag);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DiagVeiculo(
                        rs.getInt("id_diag"),
                        rs.getString("nome"),
                        rs.getString("comp_bordo"),
                        rs.getInt("codigo_falha"),
                        rs.getString("sintomas"),
                        rs.getInt("veiculo_placa")
                    );
                }
            }
        }
        return null;
    }

    // Método para listar todos os diagnósticos de veículos
    public List<DiagVeiculo> listarTodos() throws SQLException {
        List<DiagVeiculo> diagVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM DIAG_VEICULO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                diagVeiculos.add(new DiagVeiculo(
                    rs.getInt("id_diag"),
                    rs.getString("nome"),
                    rs.getString("comp_bordo"),
                    rs.getInt("codigo_falha"),
                    rs.getString("sintomas"),
                    rs.getInt("veiculo_placa")
                ));
            }
        }
        return diagVeiculos;
    }
}
