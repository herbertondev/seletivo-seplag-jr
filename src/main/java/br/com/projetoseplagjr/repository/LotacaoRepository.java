package br.com.projetoseplagjr.repository;

import br.com.projetoseplagjr.model.Lotacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LotacaoRepository implements PanacheRepository<Lotacao> {
}