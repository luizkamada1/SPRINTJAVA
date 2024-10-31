package br.com.fiap.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.beans.Veiculo;
import br.com.fiap.bo.VeiculoBo;
import br.com.fiap.conexoes.ConexaoFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculos")
public class VeiculoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() {
        try (Connection connection = new ConexaoFactory().conexao()) {
            VeiculoBo veiculoBo = new VeiculoBo(connection);
            List<Veiculo> veiculos = veiculoBo.listarTodosVeiculos();
            return Response.ok(veiculos).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar veículos.").build();
        }
    }

    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculo(@PathParam("placa") String placa) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            VeiculoBo veiculoBo = new VeiculoBo(connection);
            Veiculo veiculo = veiculoBo.buscarVeiculoPorPlaca(placa);
            if (veiculo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
            }
            return Response.ok(veiculo).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar veículo.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarVeiculo(Veiculo veiculo) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            VeiculoBo veiculoBo = new VeiculoBo(connection);
            veiculoBo.adicionarVeiculo(veiculo);
            return Response.status(Response.Status.CREATED).entity("Veículo adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar veículo.").build();
        }
    }

    @PUT
    @Path("/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("placa") String placa, Veiculo veiculo) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            VeiculoBo veiculoBo = new VeiculoBo(connection);
            veiculo.setPlaca(placa);
            veiculoBo.atualizarVeiculo(veiculo);
            return Response.ok("Veículo atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar veículo.").build();
        }
    }

    @DELETE
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarVeiculo(@PathParam("placa") String placa) {
        try (Connection connection = new ConexaoFactory().conexao()) {
            VeiculoBo veiculoBo = new VeiculoBo(connection);
            veiculoBo.deletarVeiculo(placa);
            return Response.ok("Veículo deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar veículo.").build();
        }
    }
}
