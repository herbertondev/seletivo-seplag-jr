package br.com.projetoseplagjr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import br.com.projetoseplagjr.model.UserRole;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private boolean ativo = true;
}
