package br.com.fiap.controllers;

import br.com.fiap.dtos.AluguelDto;
import br.com.fiap.exceptions.AluguelNotFound;
import br.com.fiap.exceptions.AluguelNotSavedException;
import br.com.fiap.exceptions.AluguelServiceException;
import br.com.fiap.models.Aluguel;
import br.com.fiap.service.AluguelService;
import br.com.fiap.service.AluguelServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/aluguel")
class AluguelController {

    private AluguelService aluguelService = AluguelServiceFactory.create();

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosAlugueis() {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(this.aluguelService.listarTodos())
                    .build();
        } catch (AluguelServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao listar os aluguéis"))
                    .build();
        }
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAluguel(AluguelDto dto) {
        try {
            Aluguel aluguel = new Aluguel(dto.getId(), dto.getIdBicicleta(), dto.getIdUsuario(), dto.getInicioAluguel());
            aluguel = this.aluguelService.create(aluguel);
            return Response.status(Response.Status.CREATED)
                    .entity(aluguel)
                    .build();
        } catch (AluguelNotSavedException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Erro ao salvar o aluguel"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro de banco de dados"))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAluguel(@PathParam("id") int id, AluguelDto dto) {
        try {
            Aluguel aluguel = new Aluguel(id, dto.getIdBicicleta(), dto.getIdUsuario(), dto.getInicioAluguel());
            aluguel = this.aluguelService.update(aluguel);
            return Response.status(Response.Status.OK)
                    .entity(aluguel)
                    .build();
        } catch (AluguelNotFound e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Aluguel não encontrado"))
                    .build();
        } catch (AluguelNotSavedException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao atualizar o aluguel"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAluguel(@PathParam("id") int id) {
        try {
            this.aluguelService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (AluguelNotFound e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Aluguel não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao deletar o aluguel"))
                    .build();
        }
    }
}
