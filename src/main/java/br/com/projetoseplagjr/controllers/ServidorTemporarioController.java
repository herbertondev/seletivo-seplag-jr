package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.ServidorTemporarioDTO;
import br.com.projetoseplagjr.service.ServidorTemporarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.security.Authenticated;

import java.util.List;

@Path("/servidores-temporarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ServidorTemporarioController {

    @Inject
    ServidorTemporarioService servidorTemporarioService;

    @POST
    public Response salvarServidor(ServidorTemporarioDTO servidorTemporarioDTO) {
        ServidorTemporarioDTO criado = servidorTemporarioService.criar(servidorTemporarioDTO);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @GET
    public List<ServidorTemporarioDTO> listarTodosServidor() {
        return servidorTemporarioService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public ServidorTemporarioDTO buscarServidorPorId(@PathParam("id") Long id) {
        return servidorTemporarioService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public ServidorTemporarioDTO atualizarServidor(@PathParam("id") Long id, ServidorTemporarioDTO servidorTemporarioDTO) {
        return servidorTemporarioService.atualizar(id, servidorTemporarioDTO);
    }
}