package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class LoginDTO {

    @Schema(example = "joaosilva")
    private String username;
    @Schema(example = "senha123")
    private String password;
}
