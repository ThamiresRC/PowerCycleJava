package br.com.fiap.controllers;

import br.com.fiap.dtos.BicicletaDto;
import br.com.fiap.exceptions.BicicletaNaoDisponivelException;
import br.com.fiap.service.BicicletaService;
import br.com.fiap.service.BicicletaServiceFactory;
import br.com.fiap.models.Bicicleta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/bicicleta")
class BicicletaController {

    private BicicletaService bicicletaService = BicicletaServiceFactory.create();

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodasBicicletas() {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(this.bicicletaService.listarTodas())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar as bicicletas.")
                    .build();
        }
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBicicleta(BicicletaDto dto) {
        try {
            Bicicleta bicicleta = new Bicicleta(dto.getId(), dto.getModelo(), dto.isDisponivel());
            bicicleta.setEnergiaAcumulada(dto.getEnergiaAcumulada());

            bicicleta = this.bicicletaService.create(bicicleta);
            return Response.status(Response.Status.CREATED)
                    .entity(bicicleta)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar a bicicleta.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBicicleta(@PathParam("id") int id, BicicletaDto dto) {
        try {
            Bicicleta bicicleta = new Bicicleta(id, dto.getModelo(), dto.isDisponivel());
            bicicleta.setEnergiaAcumulada(dto.getEnergiaAcumulada());

            bicicleta = this.bicicletaService.update(bicicleta);
            return Response.status(Response.Status.OK)
                    .entity(bicicleta)
                    .build();
        } catch (BicicletaNaoDisponivelException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Bicicleta não disponível para atualização.")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar a bicicleta.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBicicleta(@PathParam("id") int id) {
        try {
            this.bicicletaService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar a bicicleta.")
                    .build();
        }
    }
}
