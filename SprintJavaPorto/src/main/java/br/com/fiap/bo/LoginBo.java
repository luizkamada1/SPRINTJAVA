package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Login;
import br.com.fiap.dao.LoginDao;

public class LoginBo {

    private LoginDao loginDao;

    public LoginBo(Connection connection) {
        this.loginDao = new LoginDao(connection);
    }

    public void adicionarLogin(Login login) throws SQLException {
        if (login == null) {
            throw new IllegalArgumentException("O login não pode ser nulo.");
        }
        if (login.getUsuario() == null || login.getUsuario().isEmpty()) {
            throw new IllegalArgumentException("O usuário é obrigatório.");
        }
        if (login.getSenha() == null || login.getSenha().isEmpty()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
        if (buscarPorUsuario(login.getUsuario()) != null) {
            throw new IllegalArgumentException("Usuário já cadastrado.");
        }
        loginDao.inserir(login);
    }

    public void atualizarLogin(Login login) throws SQLException {
        if (login == null) {
            throw new IllegalArgumentException("O login não pode ser nulo.");
        }
        if (login.getUsuario() == null || login.getUsuario().isEmpty()) {
            throw new IllegalArgumentException("O usuário é obrigatório.");
        }
        if (buscarPorUsuario(login.getUsuario()) == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        loginDao.atualizar(login);
    }

    public void deletarLogin(String usuario) throws SQLException {
        if (usuario == null || usuario.isEmpty()) {
            throw new IllegalArgumentException("O usuário é obrigatório para deletar.");
        }
        if (buscarPorUsuario(usuario) == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        loginDao.deletar(usuario);
    }

    public Login buscarPorUsuario(String usuario) throws SQLException {
        if (usuario == null || usuario.isEmpty()) {
            throw new IllegalArgumentException("O usuário é obrigatório para a busca.");
        }
        return loginDao.buscarPorUsuario(usuario);
    }

    public List<Login> listarTodosLogins() throws SQLException {
        return loginDao.listarTodos();
    }
}
