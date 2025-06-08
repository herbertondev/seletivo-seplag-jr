package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.FotoResponseDTO;
import br.com.projetoseplagjr.model.FotoPessoa;
import br.com.projetoseplagjr.service.FotoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import io.quarkus.security.Authenticated;

@Path("/fotos")
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class FotoController {

    @Inject
    FotoService fotoService;

    @POST
    @Path("/upload/{pessoaId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFotos(@PathParam("pessoaId") Long pessoaId, @RestForm("arquivoMinio") FileUpload arquivo) throws Exception {
        FotoPessoa foto = fotoService.uploadFotosPessoa(pessoaId, arquivo);
        return Response.ok(foto).build();
    }

    @GET
    @Path("/{pessoaId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response buscarFotoPessoa(@PathParam("pessoaId") Long pessoaId) throws Exception {
        try {
            FotoResponseDTO fotoResponse = fotoService.buscarFotoPorPessoaId(pessoaId);
            return Response.ok(fotoResponse.getImagem())
                    .header("Content-Disposition", "attachment; filename=\"foto-" + pessoaId + ".jpg\"")
                    .header("X-Pessoa-Nome", fotoResponse.getNomePessoa())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Falha na busca\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}