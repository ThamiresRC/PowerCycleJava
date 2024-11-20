package br.com.fiap.controllers;
import br.com.fiap.dtos.UsuarioDto;
import br.com.fiap.exceptions.EmailJaCadastradoException;
import br.com.fiap.exceptions.IdadeInvalidaException;
import br.com.fiap.models.Usuario;
import br.com.fiap.service.UsuarioCadastroService;
import br.com.fiap.service.UsuarioCadastroServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario-cadastro")
class UsuarioCadastroController {

    private UsuarioCadastroService usuarioCadastroService = UsuarioCadastroServiceFactory.create();

    @POST
    @Path("/cadastro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioDto usuarioDto) {
        try {
            Usuario usuario = new Usuario(
                    usuarioDto.getId(),
                    usuarioDto.getNome(),
                    usuarioDto.getEmail(),
                    usuarioDto.getIdade(),
                    usuarioDto.getCpf()
            );

            usuarioCadastroService.cadastrarUsuario(usuario);

            return Response.status(Response.Status.CREATED)
                    .entity("Usuário cadastrado com sucesso!")
                    .build();
        } catch (EmailJaCadastradoException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: " + e.getMessage())
                    .build();
        } catch (IdadeInvalidaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar o usuário: " + e.getMessage())
                    .build();
        }
    }
}
