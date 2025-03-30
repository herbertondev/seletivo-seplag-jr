package br.com.projetoseplagjr.repository;

import br.com.projetoseplagjr.model.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

    public List<Pessoa> buscarPessoasPorNome(String nome, Page page) {
        return find("upper(nome) like ?1", "%" + nome.toUpperCase() + "%")
                .page(page) // Aplica a paginação
                .list();
    }
}