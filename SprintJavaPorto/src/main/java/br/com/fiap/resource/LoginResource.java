package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.Login;
import br.com.fiap.bo.LoginBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/logins")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarLogin(Login login) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            LoginBo loginBo = new LoginBo(connection);
            loginBo.adicionarLogin(login);
            return Response.status(Response.Status.CREATED).entity("Login criado com sucesso.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar login.").build();
        }
    }

    @GET
    @Path("/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorUsuario(@PathParam("usuario") String usuario) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            LoginBo loginBo = new LoginBo(connection);
            Login login = loginBo.buscarPorUsuario(usuario);
            if (login == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
            }
            return Response.ok(login).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar usuário.").build();
        }
    }

    @PUT
    @Path("/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarLogin(@PathParam("usuario") String usuario, Login login) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            LoginBo loginBo = new LoginBo(connection);
            login.setUsuario(usuario);
            loginBo.atualizarLogin(login);
            return Response.ok("Login atualizado com sucesso.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar login.").build();
        }
    }

    @DELETE
    @Path("/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarLogin(@PathParam("usuario") String usuario) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            LoginBo loginBo = new LoginBo(connection);
            loginBo.deletarLogin(usuario);
            return Response.ok("Login deletado com sucesso.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar login.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosLogins() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            LoginBo loginBo = new LoginBo(connection);
            List<Login> logins = loginBo.listarTodosLogins();
            return Response.ok(logins).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar logins.").build();
        }
    }
}
