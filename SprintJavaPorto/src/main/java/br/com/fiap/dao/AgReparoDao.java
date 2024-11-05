package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import br.com.fiap.beans.AgReparo;

public class AgReparoDao {

    private Connection connection;

    public AgReparoDao(Connection connection) {
        this.connection = connection;
    }

    public void inserir(AgReparo agReparo) throws SQLException {
        String sql = "INSERT INTO AGREPARO (id_reparo, data_ag, hora_ag, cliente_cpf, centro_auto_id_centro) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agReparo.getIdReparo());
            stmt.setDate(2, new Date(agReparo.getDataAg().getTime())); // Convertendo java.util.Date para java.sql.Date
            stmt.setString(3,agReparo.getHoraAg()); 
            stmt.setInt(4, agReparo.getClienteCpf());
            stmt.setInt(5, agReparo.getCentroAutoIdCentro());
            stmt.executeUpdate();
        }
    }

    public void atualizar(AgReparo agReparo) throws SQLException {
        String sql = "UPDATE AGREPARO SET data_ag=?, hora_ag=?, cliente_cpf=?, centro_auto_id_centro=? WHERE id_reparo=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new Date(agReparo.getDataAg().getTime())); // Convertendo java.util.Date para java.sql.Date
            stmt.setString(3,agReparo.getHoraAg()); 
            stmt.setInt(3, agReparo.getClienteCpf());
            stmt.setInt(4, agReparo.getCentroAutoIdCentro());
            stmt.setInt(5, agReparo.getIdReparo());
            stmt.executeUpdate();
        }
    }

    public void deletar(int idReparo) throws SQLException {
        String sql = "DELETE FROM AGREPARO WHERE id_reparo=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReparo);
            stmt.executeUpdate();
        }
    }

    public AgReparo buscarPorId(int idReparo) throws SQLException {
        String sql = "SELECT * FROM AGREPARO WHERE id_reparo=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idReparo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AgReparo(
                        rs.getInt("id_reparo"),
                        rs.getDate("data_ag"),
                        rs.getString("hora_ag"),
                        rs.getInt("cliente_cpf"),
                        rs.getInt("centro_auto_id_centro")
                    );
                }
            }
        }
        return null;
    }

    public List<AgReparo> listarTodos() throws SQLException {
        List<AgReparo> agReparos = new ArrayList<>();
        String sql = "SELECT * FROM AGREPARO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                agReparos.add(new AgReparo(
                    rs.getInt("id_reparo"),
                    rs.getDate("data_ag"),
                    rs.getString("hora_ag"),
                    rs.getInt("cliente_cpf"),
                    rs.getInt("centro_auto_id_centro")
                ));
            }
        }
        return agReparos;
    }

    // Listar todos os reparos de um cliente específico (por CPF)
    public List<AgReparo> listarReparosPorCliente(int clienteCpf) throws SQLException {
        List<AgReparo> agReparos = new ArrayList<>();
        String sql = "SELECT * FROM AGREPARO WHERE cliente_cpf=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteCpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    agReparos.add(new AgReparo(
                        rs.getInt("id_reparo"),
                        rs.getDate("data_ag"),
                        rs.getString("hora_ag"),
                        rs.getInt("cliente_cpf"),
                        rs.getInt("centro_auto_id_centro")
                    ));
                }
            }
        }
        return agReparos;
    }
}
