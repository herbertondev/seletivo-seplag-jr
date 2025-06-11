package br.com.projetoseplagjr.dto;

import br.com.projetoseplagjr.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class UserRequestDTO {

    @Schema(example = "usuario")
    private String username;
    @Schema(example = "123")
    private String password;
    @Schema(example = "ADMIN/USER")
    private UserRole role;
}
