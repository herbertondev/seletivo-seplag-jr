package br.com.projetoseplagjr.repository;

import br.com.projetoseplagjr.model.ServidorTemporario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServidorTemporarioRepository implements PanacheRepository<ServidorTemporario> {
}