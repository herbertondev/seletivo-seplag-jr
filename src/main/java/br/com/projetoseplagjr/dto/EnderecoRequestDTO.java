package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class EnderecoRequestDTO {

    @Schema(example = "Avenida/Rua")
    private String tipoLogradouro;
    @Schema(example = "Rua fernando de noronha")
    private String logradouro;
    @Schema(example = "770")
    private Integer numero;
    @Schema(example = "Brooklin")
    private String bairro;
    @Schema(example = "1")
    private Long cidadeId;
}