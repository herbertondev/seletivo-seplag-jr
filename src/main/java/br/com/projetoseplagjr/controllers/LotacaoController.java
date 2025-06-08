package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.LotacaoDTO;
import br.com.projetoseplagjr.dto.LotacaoResponseDTO;
import br.com.projetoseplagjr.service.LotacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.security.Authenticated;

import java.util.List;

@Path("/lotacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class LotacaoController {

    @Inject
    LotacaoService lotacaoService;

    @POST
    public Response criar(LotacaoDTO lotacaoDTO) {
        LotacaoResponseDTO criada = lotacaoService.criar(lotacaoDTO);
        return Response.status(Response.Status.CREATED).entity(criada).build();
    }

    @GET
    public List<LotacaoResponseDTO> listarTodos() {
        return lotacaoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public LotacaoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return lotacaoService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public LotacaoResponseDTO atualizar(@PathParam("id") Long id, LotacaoDTO lotacaoDTO) {
        return lotacaoService.atualizar(id, lotacaoDTO);
    }

//    @DELETE
//    @Path("/{id}")
//    public void excluir(@PathParam("id") Long id) {
//        lotacaoService.excluir(id);
//    }
}