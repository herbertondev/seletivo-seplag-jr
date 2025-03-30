package br.com.projetoseplagjr.repository;

import br.com.projetoseplagjr.model.FotoPessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FotoPessoaRepository implements PanacheRepository<FotoPessoa> {
}