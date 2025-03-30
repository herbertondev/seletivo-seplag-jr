package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.UnidadeDTO;
import br.com.projetoseplagjr.mappers.UnidadeMapper;
import br.com.projetoseplagjr.model.Unidade;
import br.com.projetoseplagjr.repository.UnidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UnidadeService {

    @Inject
    UnidadeRepository unidadeRepository;

    @Inject
    UnidadeMapper unidadeMapper;

    @Transactional
    public UnidadeDTO criar(UnidadeDTO unidadeDTO) {
        Unidade unidade = unidadeMapper.toEntity(unidadeDTO);
        unidadeRepository.persist(unidade);
        return unidadeMapper.toDTO(unidade);
    }

    public List<UnidadeDTO> listarTodos() {
        return unidadeRepository.findAll().stream()
                .map(unidadeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UnidadeDTO buscarPorId(Long id) {
        Unidade unidade = unidadeRepository.findById(id);
        return unidadeMapper.toDTO(unidade);
    }

    @Transactional
    public UnidadeDTO atualizar(Long id, UnidadeDTO unidadeDTO) {
        Unidade unidade = unidadeRepository.findById(id);
        unidadeMapper.updateEntityFromDTO(unidadeDTO, unidade);
        unidadeRepository.persist(unidade);
        return unidadeMapper.toDTO(unidade);
    }

    @Transactional
    public void excluir(Long id) {
        unidadeRepository.deleteById(id);
    }
}