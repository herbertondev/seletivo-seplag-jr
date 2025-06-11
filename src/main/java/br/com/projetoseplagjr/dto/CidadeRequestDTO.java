package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class CidadeRequestDTO {
    @Schema(example = "Cuiabá")
    private String nome;
    @Schema(example = "MT")
    private String uf;
}
