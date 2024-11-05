package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.OrcaReparo;
import br.com.fiap.bo.OrcaReparoBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/orcamentos")
public class OrcaReparoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarOrcamentos() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            OrcaReparoBo orcaReparoBo = new OrcaReparoBo(connection);
            List<OrcaReparo> orcamentos = orcaReparoBo.listarTodosOrcamentos();
            return Response.ok(orcamentos).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar orçamentos de reparo.").build();
        }
    }

    @GET
    @Path("/{idOrca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarOrcamento(@PathParam("idOrca") int idOrca) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            OrcaReparoBo orcaReparoBo = new OrcaReparoBo(connection);
            OrcaReparo orcamento = orcaReparoBo.buscarOrcamentoPorId(idOrca);
            if (orcamento == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Orçamento de reparo não encontrado.").build();
            }
            return Response.ok(orcamento).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar orçamento de reparo.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarOrcamento(OrcaReparo orcamento) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            OrcaReparoBo orcaReparoBo = new OrcaReparoBo(connection);
            orcaReparoBo.adicionarOrcamento(orcamento);
            return Response.status(Response.Status.CREATED).entity("Orçamento de reparo adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar orçamento de reparo.").build();
        }
    }

    @PUT
    @Path("/{idOrca}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarOrcamento(@PathParam("idOrca") int idOrca, OrcaReparo orcamento) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            OrcaReparoBo orcaReparoBo = new OrcaReparoBo(connection);
            orcamento.setIdOrca(idOrca);
            orcaReparoBo.atualizarOrcamento(orcamento);
            return Response.ok("Orçamento de reparo atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar orçamento de reparo.").build();
        }
    }

    @DELETE
    @Path("/{idOrca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarOrcamento(@PathParam("idOrca") int idOrca) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            OrcaReparoBo orcaReparoBo = new OrcaReparoBo(connection);
            orcaReparoBo.deletarOrcamento(idOrca);
            return Response.ok("Orçamento de reparo deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar orçamento de reparo.").build();
        }
    }
}
