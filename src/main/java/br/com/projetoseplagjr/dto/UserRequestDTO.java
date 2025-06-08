package br.com.projetoseplagjr.dto;

import br.com.projetoseplagjr.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String username;
    private String password;
    private UserRole role;
}
