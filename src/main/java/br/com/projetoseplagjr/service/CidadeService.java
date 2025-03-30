package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.CidadeRequestDTO;
import br.com.projetoseplagjr.dto.CidadeResponseDTO;
import br.com.projetoseplagjr.mappers.CidadeMapper;
import br.com.projetoseplagjr.model.Cidade;
import br.com.projetoseplagjr.repository.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CidadeService {

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    CidadeMapper cidadeMapper;

    @Transactional
    public CidadeResponseDTO salvarCidade(CidadeRequestDTO cidadeRequest) {
        Cidade cidade = cidadeMapper.toEntity(cidadeRequest);
        cidadeRepository.persist(cidade);
        return cidadeMapper.toResponse(cidade);
    }

    public List<CidadeResponseDTO> listarTodasCidade() {
        return cidadeRepository.listAll()
                .stream()
                .map(cidadeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CidadeResponseDTO buscarPorId(Long id) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada");
        }
        return cidadeMapper.toResponse(cidade);
    }

    @Transactional
    public CidadeResponseDTO atualizarCidade(Long id, CidadeRequestDTO cidadeRequest) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada");
        }
        cidade.setNome(cidadeRequest.getNome());
        cidade.setUf(cidadeRequest.getUf());
        cidadeRepository.persist(cidade);
        return cidadeMapper.toResponse(cidade);
    }
}