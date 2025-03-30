package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.UnidadeDTO;
import br.com.projetoseplagjr.service.UnidadeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/unidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeController {

    @Inject
    UnidadeService unidadeService;

    @POST
    public Response criarUnidade(UnidadeDTO unidadeDTO) {
        UnidadeDTO criada = unidadeService.criar(unidadeDTO);
        return Response.status(Response.Status.CREATED).entity(criada).build();
    }

    @GET
    public List<UnidadeDTO> listarTodos() {
        return unidadeService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public UnidadeDTO buscarPorId(@PathParam("id") Long id) {
        return unidadeService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public UnidadeDTO atualizar(@PathParam("id") Long id, UnidadeDTO unidadeDTO) {
        return unidadeService.atualizar(id, unidadeDTO);
    }
}