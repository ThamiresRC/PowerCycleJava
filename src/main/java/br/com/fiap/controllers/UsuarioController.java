package br.com.fiap.controllers;

import br.com.fiap.dtos.UsuarioDto;
import br.com.fiap.exceptions.UsuarioNotFoundException;
import br.com.fiap.models.Usuario;
import br.com.fiap.service.UsuarioService;
import br.com.fiap.service.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/usuario")
class UsuarioController {

    private UsuarioService usuarioService = UsuarioServiceFactory.create();

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosUsuarios() {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(this.usuarioService.listarTodos())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao listar os usuários"))
                    .build();
        }
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(UsuarioDto dto) {
        try {
            Usuario usuario = new Usuario(dto.getId(), dto.getNome(), dto.getEmail(), dto.getIdade(), dto.getCpf());
            usuario = this.usuarioService.create(dto);
            return Response.status(Response.Status.CREATED)
                    .entity(usuario)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Erro ao salvar o usuário"))
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
    public Response updateUsuario(@PathParam("id") int id, UsuarioDto dto) {
        try {
            Usuario usuario = new Usuario(id, dto.getNome(), dto.getEmail(), dto.getIdade(), dto.getCpf());
            usuario = this.usuarioService.update(dto);
            return Response.status(Response.Status.OK)
                    .entity(usuario)
                    .build();
        } catch (UsuarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Usuário não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao atualizar o usuário"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") int id) {
        try {
            this.usuarioService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (UsuarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Usuário não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro ao deletar o usuário"))
                    .build();
        }
    }
}
