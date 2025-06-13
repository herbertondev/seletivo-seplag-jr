package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.UserRequestDTO;
import br.com.projetoseplagjr.dto.UserResponseDTO;
import br.com.projetoseplagjr.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.security.Authenticated;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class UserController {

    @Inject
    UserService userService;

    @GET
    @RolesAllowed({"USER","ADMIN"})
    public List<UserResponseDTO> listar() {
        return userService.listarTodos();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response criar(UserRequestDTO dto) {
        UserResponseDTO saved = userService.criar(dto);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public UserResponseDTO atualizar(@PathParam("id") Long id, UserRequestDTO dto) {
        return userService.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response excluir(@PathParam("id") Long id) {
        userService.desativar(id);
        return Response.noContent().build();
    }
}
