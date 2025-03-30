package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.ServidorEfetivoDTO;
import br.com.projetoseplagjr.service.ServidorEfetivoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/servidores-efetivos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServidorEfetivoController {

    @Inject
    ServidorEfetivoService servidorEfetivoService;

    @POST
    public Response criar(ServidorEfetivoDTO servidorEfetivoDTO) {
        ServidorEfetivoDTO created = servidorEfetivoService.criar(servidorEfetivoDTO);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public List<ServidorEfetivoDTO> listarTodos() {
        return servidorEfetivoService.listarTodos();
    }

    @GET
    @Path("/{pessoaId}")
    public ServidorEfetivoDTO buscarPorId(@PathParam("pessoaId") Long pessoaId) {
        return servidorEfetivoService.buscarPorId(pessoaId); // Busca por pessoaId
    }

    @PUT
    @Path("/{id}")
    public ServidorEfetivoDTO atualizar(@PathParam("id") Long id, ServidorEfetivoDTO servidorEfetivoDTO) {
        return servidorEfetivoService.atualizar(id, servidorEfetivoDTO); // Atualiza por se_id
    }
}

