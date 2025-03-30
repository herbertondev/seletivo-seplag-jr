package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.EnderecoRequestDTO;
import br.com.projetoseplagjr.dto.EnderecoResponseDTO;
import br.com.projetoseplagjr.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoController {

    @Inject
    EnderecoService enderecoService;

    @POST
    public Response salvarEndereco(EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO response = enderecoService.salvarEndereco(enderecoRequestDTO);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    // Novo endpoint para cadastrar endereço associado a uma pessoa
    @POST
    @Path("/pessoa/{pessoaId}")
    public Response createForPessoa(@PathParam("pessoaId") Long pessoaId, EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO response = enderecoService.createForPessoa(pessoaId, enderecoRequestDTO);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    @GET
    public List<EnderecoResponseDTO> listarEndereco() {
        return enderecoService.listarEnderecos();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO bucarEnderecoPorId(@PathParam("id") Long id) {
        return enderecoService.enderecoPorId(id);
    }

    @PUT
    @Path("/{id}")
    public EnderecoResponseDTO atualizar(@PathParam("id") Long id, EnderecoRequestDTO enderecoRequest) {
        return enderecoService.atualizarEndereco(id, enderecoRequest);
    }
}