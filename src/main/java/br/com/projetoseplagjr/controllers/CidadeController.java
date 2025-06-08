package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.CidadeRequestDTO;
import br.com.projetoseplagjr.dto.CidadeResponseDTO;
import br.com.projetoseplagjr.service.CidadeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.security.Authenticated;

import java.util.List;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class CidadeController {

    @Inject
    CidadeService cidadeService;

    @POST
    public Response salvarCidade(CidadeRequestDTO cidadeRequest) {
        CidadeResponseDTO response = cidadeService.salvarCidade(cidadeRequest);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public List<CidadeResponseDTO> listarCidades() {
        return cidadeService.listarTodasCidade();
    }

    @GET
    @Path("/{id}")
    public CidadeResponseDTO buscarPorId(@PathParam("id") Long id) {
        return cidadeService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public CidadeResponseDTO atualizarCidade(@PathParam("id") Long id, CidadeRequestDTO request) {
        return cidadeService.atualizarCidade(id, request);
    }
}