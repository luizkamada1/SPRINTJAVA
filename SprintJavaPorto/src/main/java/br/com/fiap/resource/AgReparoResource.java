package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.AgReparo;
import br.com.fiap.bo.AgReparoBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/agendamentos")
public class AgReparoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAgendamentos() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);
            List<AgReparo> agendamentos = agReparoBo.listarTodosAgendamentos();
            return Response.ok(agendamentos).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar agendamentos de reparo.").build();
        }
    }

    @GET
    @Path("/{idReparo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAgendamento(@PathParam("idReparo") int idReparo) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);
            AgReparo agendamento = agReparoBo.buscarAgendamentoPorId(idReparo);
            if (agendamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Agendamento de reparo não encontrado.").build();
            }
            return Response.ok(agendamento).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar agendamento de reparo.").build();
        }
    }

    @GET
    @Path("/cliente/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAgendamentosPorCliente(@PathParam("cpf") int cpf) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);
            List<AgReparo> agendamentos = agReparoBo.listarAgendamentosPorCliente(cpf);
            return Response.ok(agendamentos).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar agendamentos de reparo para o cliente.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarAgendamento(AgReparo agendamento) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);
            agReparoBo.adicionarAgendamento(agendamento);
            return Response.status(Response.Status.CREATED).entity("Agendamento de reparo adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar agendamento de reparo.").build();
        }
    }

    @PUT
    @Path("/{idReparo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarAgendamento(@PathParam("idReparo") int idReparo, AgReparo agendamento) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);
            agendamento.setIdReparo(idReparo);
            agReparoBo.atualizarAgendamento(agendamento);
            return Response.ok("Agendamento de reparo atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar agendamento de reparo.").build();
        }
    }

    @DELETE
    @Path("/{idReparo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarAgendamento(@PathParam("idReparo") int idReparo) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            AgReparoBo agReparoBo = new AgReparoBo(connection);
            agReparoBo.deletarAgendamento(idReparo);
            return Response.ok("Agendamento de reparo deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar agendamento de reparo.").build();
        }
    }
}
