package br.com.projetoseplagjr.dto;

import br.com.projetoseplagjr.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String username;
    private UserRole role;
    private boolean ativo;
}
