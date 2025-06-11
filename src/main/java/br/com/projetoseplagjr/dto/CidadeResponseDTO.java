package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class CidadeResponseDTO {
    @Schema(example = "1 ")
    private Long id;
    @Schema(example = "São Paulo ")
    private String nome;
    @Schema(example = "SP")
    private String uf;
}
