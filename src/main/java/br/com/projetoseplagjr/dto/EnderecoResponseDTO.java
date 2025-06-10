package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class EnderecoResponseDTO {

    @Schema(example = "id")
    private Long id;
    @Schema(example = "Avenida/Rua")
    private String tipoLogradouro;
    @Schema(example = "Rua fernando de noronha")
    private String logradouro;
    @Schema(example = "770")
    private Integer numero;
    @Schema(example = "Brooklin")
    private String bairro;
    @Schema(example = "São Paulo")
    private String cidadeNome;
}
