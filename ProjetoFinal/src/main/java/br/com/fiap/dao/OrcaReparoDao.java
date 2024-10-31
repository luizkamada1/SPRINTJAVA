package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.beans.OrcaReparo;

public class OrcaReparoDao {

    private Connection connection;

    public OrcaReparoDao(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um orçamento de reparo
    public void inserir(OrcaReparo orcaReparo) throws SQLException {
        String sql = "INSERT INTO ORCA_REPARO (id_orca, preco_estim, reparo_embr, reparo_rad, subs_correia, diag_veiculo_id_diagnostico) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orcaReparo.getIdOrca());
            stmt.setDouble(2, orcaReparo.getPrecoEstim());
            stmt.setString(3, orcaReparo.getReparoEmbr());
            stmt.setString(4, orcaReparo.getReparoRad());
            stmt.setString(5, orcaReparo.getSubsCorreia());
            stmt.setInt(6, orcaReparo.getDiagVeiculoIdDiagnostico());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um orçamento de reparo
    public void atualizar(OrcaReparo orcaReparo) throws SQLException {
        String sql = "UPDATE ORCA_REPARO SET preco_estim=?, reparo_embr=?, reparo_rad=?, subs_correia=?, diag_veiculo_id_diagnostico=? WHERE id_orca=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, orcaReparo.getPrecoEstim());
            stmt.setString(2, orcaReparo.getReparoEmbr());
            stmt.setString(3, orcaReparo.getReparoRad());
            stmt.setString(4, orcaReparo.getSubsCorreia());
            stmt.setInt(5, orcaReparo.getDiagVeiculoIdDiagnostico());
            stmt.setInt(6, orcaReparo.getIdOrca());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um orçamento de reparo pelo ID
    public void deletar(int idOrca) throws SQLException {
        String sql = "DELETE FROM ORCA_REPARO WHERE id_orca=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idOrca);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um orçamento de reparo pelo ID
    public OrcaReparo buscarPorId(int idOrca) throws SQLException {
        String sql = "SELECT * FROM ORCA_REPARO WHERE id_orca=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idOrca);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrcaReparo(
                        rs.getInt("id_orca"),
                        rs.getDouble("preco_estim"),
                        rs.getString("reparo_embr"),
                        rs.getString("reparo_rad"),
                        rs.getString("subs_correia"),
                        rs.getInt("diag_veiculo_id_diagnostico")
                    );
                }
            }
        }
        return null;
    }

    // Método para listar todos os orçamentos de reparos
    public List<OrcaReparo> listarTodos() throws SQLException {
        List<OrcaReparo> orcaReparos = new ArrayList<>();
        String sql = "SELECT * FROM ORCA_REPARO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orcaReparos.add(new OrcaReparo(
                    rs.getInt("id_orca"),
                    rs.getDouble("preco_estim"),
                    rs.getString("reparo_embr"),
                    rs.getString("reparo_rad"),
                    rs.getString("subs_correia"),
                    rs.getInt("diag_veiculo_id_diagnostico")
                ));
            }
        }
        return orcaReparos;
    }
}
