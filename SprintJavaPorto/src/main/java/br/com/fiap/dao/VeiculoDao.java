package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.beans.Veiculo;

public class VeiculoDao {

    private Connection connection;

    public VeiculoDao(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO VEICULO (placa, marca, modelo, ano, clienteCpf) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAno());
            stmt.setString(5, veiculo.getClienteCpf());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE VEICULO SET marca=?, modelo=?, ano=?, clienteCpf=? WHERE placa=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getClienteCpf());
            stmt.setString(5, veiculo.getPlaca());
            stmt.executeUpdate();
        }
    }

    public void deletar(String placa) throws SQLException {
        String sql = "DELETE FROM VEICULO WHERE placa=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
        }
    }

    public Veiculo buscarPorPlaca(String placa) throws SQLException {
        String sql = "SELECT * FROM VEICULO WHERE placa=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Veiculo(
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("clienteCpf")
                    );
                }
            }
        }
        return null;
    }

    public List<Veiculo> listarTodos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEICULO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                veiculos.add(new Veiculo(
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getString("clienteCpf")
                ));
            }
        }
        return veiculos;
    }

    // Listar todos os veículos de um cliente específico (por CPF)
    public List<Veiculo> listarVeiculosPorCliente(String clienteCpf) throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEICULO WHERE clienteCpf=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clienteCpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    veiculos.add(new Veiculo(
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("clienteCpf")
                    ));
                }
            }
        }
        return veiculos;
    }
}
