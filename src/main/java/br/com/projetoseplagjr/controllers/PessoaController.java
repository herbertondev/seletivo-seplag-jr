package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.PessoaRequestDTO;
import br.com.projetoseplagjr.dto.PessoaResponseDTO;
import br.com.projetoseplagjr.service.PessoaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.security.Authenticated;

import java.util.List;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class PessoaController {

    @Inject
    PessoaService pessoaService;

    @GET
    public List<PessoaResponseDTO> listarTodasPessoas(@QueryParam("page") @DefaultValue("0") int page,
                                                      @QueryParam("size") @DefaultValue("10") int size) {
        return pessoaService.listarTodasPessoas(page, size);
    }

    @GET
    @Path("/{id}")
    public PessoaResponseDTO buscarPessoaPorId(@PathParam("id") Long id) {
        return pessoaService.buscarPessoaPorId(id);
    }

    @POST
    public Response salvarPessoa(PessoaRequestDTO pessoaDTO) {
        PessoaResponseDTO saved = pessoaService.salvarPessoa(pessoaDTO);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @PUT
    @Path("/{id}")
    public PessoaResponseDTO atualizarPessoa(@PathParam("id") Long id, PessoaRequestDTO pessoaDTO) {
        return pessoaService.atualizarPessoa(id, pessoaDTO);
    }

    @GET
    @Path("/buscar")
    public List<PessoaResponseDTO> buscarPessoasPorNome(@QueryParam("nome") String nome,
                                                        @QueryParam("page") @DefaultValue("0") int page,
                                                        @QueryParam("size") @DefaultValue("10") int size) {
        return pessoaService.buscarPessoasPorNome(nome, page, size);
    }
}