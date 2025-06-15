package br.com.projetoseplagjr.controllers;

import br.com.projetoseplagjr.dto.LoginDTO;
import br.com.projetoseplagjr.dto.TokenDTO;
import br.com.projetoseplagjr.model.User;
import br.com.projetoseplagjr.repository.UserRepository;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Duration;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/login")
    @Transactional
    public Response login(LoginDTO credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null || !user.isAtivo() || !user.getPassword().equals(credentials.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String token = Jwt.claims()
                .subject(credentials.getUsername())
                .groups(user.getRole().name())
                .expiresIn(Duration.ofMinutes(5))
                .sign();
        return Response.ok(new TokenDTO(token)).build();
    }
}
