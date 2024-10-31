package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.Cliente;
import br.com.fiap.bo.ClienteBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            ClienteBo clienteBo = new ClienteBo(connection);
            List<Cliente> clientes = clienteBo.listarTodosClientes();
            return Response.ok(clientes).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar clientes.").build();
        }
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@PathParam("cpf") String cpf) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            ClienteBo clienteBo = new ClienteBo(connection);
            Cliente cliente = clienteBo.buscarClientePorCpf(cpf);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado.").build();
            }
            return Response.ok(cliente).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar cliente.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarCliente(Cliente cliente) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            ClienteBo clienteBo = new ClienteBo(connection);
            clienteBo.adicionarCliente(cliente);
            return Response.status(Response.Status.CREATED).entity("Cliente adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar cliente.").build();
        }
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCliente(@PathParam("cpf") String cpf, Cliente cliente) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            ClienteBo clienteBo = new ClienteBo(connection);
            cliente.setCpf(cpf);
            clienteBo.atualizarCliente(cliente);
            return Response.ok("Cliente atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar cliente.").build();
        }
    }

    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarCliente(@PathParam("cpf") String cpf) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            ClienteBo clienteBo = new ClienteBo(connection);
            clienteBo.deletarCliente(cpf);
            return Response.ok("Cliente deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar cliente.").build();
        }
    }
}
