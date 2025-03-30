package br.com.projetoseplagjr.repository;

import br.com.projetoseplagjr.model.Unidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnidadeRepository implements PanacheRepository<Unidade> {
}