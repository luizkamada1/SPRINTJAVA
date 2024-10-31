package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.CentroAuto;
import br.com.fiap.bo.CentroAutoBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/centros-auto")
public class CentroAutoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCentrosAuto() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            CentroAutoBo centroAutoBo = new CentroAutoBo(connection);
            List<CentroAuto> centros = centroAutoBo.listarTodosCentros();
            return Response.ok(centros).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar centros automotivos.").build();
        }
    }

    @GET
    @Path("/{idCentro}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCentroAuto(@PathParam("idCentro") int idCentro) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            CentroAutoBo centroAutoBo = new CentroAutoBo(connection);
            CentroAuto centro = centroAutoBo.buscarCentroPorId(idCentro);
            if (centro == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Centro automotivo não encontrado.").build();
            }
            return Response.ok(centro).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar centro automotivo.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarCentroAuto(CentroAuto centroAuto) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            CentroAutoBo centroAutoBo = new CentroAutoBo(connection);
            centroAutoBo.adicionarCentro(centroAuto);
            return Response.status(Response.Status.CREATED).entity("Centro automotivo adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar centro automotivo.").build();
        }
    }

    @PUT
    @Path("/{idCentro}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCentroAuto(@PathParam("idCentro") int idCentro, CentroAuto centroAuto) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            CentroAutoBo centroAutoBo = new CentroAutoBo(connection);
            centroAuto.setIdCentro(idCentro);
            centroAutoBo.atualizarCentro(centroAuto);
            return Response.ok("Centro automotivo atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar centro automotivo.").build();
        }
    }

    @DELETE
    @Path("/{idCentro}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarCentroAuto(@PathParam("idCentro") int idCentro) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            CentroAutoBo centroAutoBo = new CentroAutoBo(connection);
            centroAutoBo.deletarCentro(idCentro);
            return Response.ok("Centro automotivo deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar centro automotivo.").build();
        }
    }
}
