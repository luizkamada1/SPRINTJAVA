package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.beans.CentroAuto;

public class CentroAutoDao {

    private Connection connection;

    public CentroAutoDao(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um centro automotivo
    public void inserir(CentroAuto centroAuto) throws SQLException {
        String sql = "INSERT INTO CENTRO_AUTO (id_centro, nome, endereco, cep) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, centroAuto.getIdCentro());
            stmt.setString(2, centroAuto.getNome());
            stmt.setString(3, centroAuto.getEndereco());
            stmt.setString(4, centroAuto.getCep());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um centro automotivo
    public void atualizar(CentroAuto centroAuto) throws SQLException {
        String sql = "UPDATE CENTRO_AUTO SET nome=?, endereco=?, cep=? WHERE id_centro=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, centroAuto.getNome());
            stmt.setString(2, centroAuto.getEndereco());
            stmt.setString(3, centroAuto.getCep());
            stmt.setInt(4, centroAuto.getIdCentro());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um centro automotivo pelo ID
    public void deletar(int idCentro) throws SQLException {
        String sql = "DELETE FROM CENTRO_AUTO WHERE id_centro=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCentro);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um centro automotivo pelo ID
    public CentroAuto buscarPorId(int idCentro) throws SQLException {
        String sql = "SELECT * FROM CENTRO_AUTO WHERE id_centro=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCentro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CentroAuto(
                        rs.getInt("id_centro"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("cep")
                    );
                }
            }
        }
        return null;
    }

    // Método para listar todos os centros automotivos
    public List<CentroAuto> listarTodos() throws SQLException {
        List<CentroAuto> centrosAuto = new ArrayList<>();
        String sql = "SELECT * FROM CENTRO_AUTO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                centrosAuto.add(new CentroAuto(
                    rs.getInt("id_centro"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("cep")
                ));
            }
        }
        return centrosAuto;
    }
}
