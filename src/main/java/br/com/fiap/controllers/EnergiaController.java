package br.com.fiap.controllers;

import br.com.fiap.dtos.EnergiaDto;
import br.com.fiap.exceptions.EnergiaNotFoundException;
import br.com.fiap.exceptions.EnergiaNotSavedException;
import br.com.fiap.exceptions.EnergiaServiceException;
import br.com.fiap.service.EnergiaService;
import br.com.fiap.service.EnergiaServiceFactory;
import br.com.fiap.models.Energia;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/energia")
class EnergiaController {

    private EnergiaService energiaService = EnergiaServiceFactory.create();

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodasEnergias() {
        try {
            List<Energia> energias = energiaService.listarTodos();
            return Response
                    .status(Response.Status.OK)
                    .entity(energias)
                    .build();
        } catch (EnergiaServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar as energias.")
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEnergia(EnergiaDto dto) {
        try {
            Energia energia = new Energia(dto.getId(), dto.getIdBicicleta(), dto.getEnergiaGerada(), dto.getDataGeracao());
            energia = energiaService.create(energia);
            return Response.status(Response.Status.CREATED)
                    .entity(energia)
                    .build();
        } catch (EnergiaNotSavedException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao salvar a energia.")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar a energia.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEnergia(@PathParam("id") int id, EnergiaDto dto) {
        try {
            Energia energia = new Energia(id, dto.getIdBicicleta(), dto.getEnergiaGerada(), dto.getDataGeracao());
            energia = energiaService.update(energia);
            return Response.status(Response.Status.OK)
                    .entity(energia)
                    .build();
        } catch (EnergiaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Energia não encontrada.")
                    .build();
        } catch (EnergiaNotSavedException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar a energia.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEnergia(@PathParam("id") int id) {
        try {
            energiaService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EnergiaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Energia não encontrada.")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar a energia.")
                    .build();
        }
    }
}
