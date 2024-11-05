package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.beans.Login;

public class LoginDao {

    private Connection connection;

    public LoginDao(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Login login) throws SQLException {
        String sql = "INSERT INTO LOGIN (nome, usuario, email, senha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login.getNome());
            stmt.setString(2, login.getUsuario());
            stmt.setString(3, login.getEmail());
            stmt.setString(4, login.getSenha());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Login login) throws SQLException {
        String sql = "UPDATE LOGIN SET nome = ?, email = ?, senha = ? WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login.getNome());
            stmt.setString(2, login.getEmail());
            stmt.setString(3, login.getSenha());
            stmt.setString(4, login.getUsuario());
            stmt.executeUpdate();
        }
    }

    public void deletar(String usuario) throws SQLException {
        String sql = "DELETE FROM LOGIN WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.executeUpdate();
        }
    }

    public Login buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM LOGIN WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Login(
                        rs.getString("nome"),
                        rs.getString("usuario"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        }
        return null;
    }

    public List<Login> listarTodos() throws SQLException {
        List<Login> logins = new ArrayList<>();
        String sql = "SELECT * FROM LOGIN";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                logins.add(new Login(
                    rs.getString("nome"),
                    rs.getString("usuario"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }
        }
        return logins;
    }
}
