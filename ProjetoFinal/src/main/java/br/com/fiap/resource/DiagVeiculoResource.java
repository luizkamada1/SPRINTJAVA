package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.DiagVeiculo;
import br.com.fiap.bo.DiagVeiculoBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/diagnosticos")
public class DiagVeiculoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDiagnosticos() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            DiagVeiculoBo diagVeiculoBo = new DiagVeiculoBo(connection);
            List<DiagVeiculo> diagnosticos = diagVeiculoBo.listarTodosDiagnosticos();
            return Response.ok(diagnosticos).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar diagnósticos de veículos.").build();
        }
    }

    @GET
    @Path("/{idDiag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarDiagnostico(@PathParam("idDiag") int idDiag) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            DiagVeiculoBo diagVeiculoBo = new DiagVeiculoBo(connection);
            DiagVeiculo diagnostico = diagVeiculoBo.buscarDiagnosticoPorId(idDiag);
            if (diagnostico == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Diagnóstico de veículo não encontrado.").build();
            }
            return Response.ok(diagnostico).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar diagnóstico de veículo.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarDiagnostico(DiagVeiculo diagnostico) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            DiagVeiculoBo diagVeiculoBo = new DiagVeiculoBo(connection);
            diagVeiculoBo.adicionarDiagnostico(diagnostico);
            return Response.status(Response.Status.CREATED).entity("Diagnóstico de veículo adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar diagnóstico de veículo.").build();
        }
    }

    @PUT
    @Path("/{idDiag}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarDiagnostico(@PathParam("idDiag") int idDiag, DiagVeiculo diagnostico) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            DiagVeiculoBo diagVeiculoBo = new DiagVeiculoBo(connection);
            diagnostico.setIdDiag(idDiag);
            diagVeiculoBo.atualizarDiagnostico(diagnostico);
            return Response.ok("Diagnóstico de veículo atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar diagnóstico de veículo.").build();
        }
    }

    @DELETE
    @Path("/{idDiag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarDiagnostico(@PathParam("idDiag") int idDiag) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            DiagVeiculoBo diagVeiculoBo = new DiagVeiculoBo(connection);
            diagVeiculoBo.deletarDiagnostico(idDiag);
            return Response.ok("Diagnóstico de veículo deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar diagnóstico de veículo.").build();
        }
    }
}
