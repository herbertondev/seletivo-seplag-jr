package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.ServidorTemporarioDTO;
import br.com.projetoseplagjr.mappers.ServidorTemporarioMapper;
import br.com.projetoseplagjr.model.Pessoa;
import br.com.projetoseplagjr.model.ServidorTemporario;
import br.com.projetoseplagjr.repository.PessoaRepository;
import br.com.projetoseplagjr.repository.ServidorTemporarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServidorTemporarioService {

    @Inject
    ServidorTemporarioMapper servidorTemporarioMapper;

    @Inject
    ServidorTemporarioRepository servidorTemporarioRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Transactional
    public ServidorTemporarioDTO criar(ServidorTemporarioDTO servidorTemporarioDTO) {
        if (servidorTemporarioDTO.getPessoaId() == null) {
            throw new IllegalArgumentException("O ID da pessoa é obrigatório");
        }

        Pessoa pessoa = pessoaRepository.findByIdOptional(servidorTemporarioDTO.getPessoaId())
                .orElseThrow(() -> new NotFoundException("Pessoa com ID " + servidorTemporarioDTO.getPessoaId() + " não encontrada"));

        ServidorTemporario servidorTemporario = servidorTemporarioMapper.toEntity(servidorTemporarioDTO);
        servidorTemporario.setPessoa(pessoa);

        servidorTemporarioRepository.persist(servidorTemporario);

        return servidorTemporarioMapper.toDTO(servidorTemporario);
    }

    public List<ServidorTemporarioDTO> listarTodos() {
        return servidorTemporarioRepository.findAll().stream()
                .map(servidorTemporarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServidorTemporarioDTO buscarPorId(Long id) {
        ServidorTemporario servidorTemporario = servidorTemporarioRepository.findById(id);
        return servidorTemporarioMapper.toDTO(servidorTemporario);
    }

    @Transactional
    public ServidorTemporarioDTO atualizar(Long id, ServidorTemporarioDTO servidorTemporarioDTO) {
        ServidorTemporario servidorTemporario = servidorTemporarioRepository.findById(id);
        servidorTemporarioMapper.updateEntityFromDTO(servidorTemporarioDTO, servidorTemporario);
        servidorTemporarioRepository.persist(servidorTemporario);
        return servidorTemporarioMapper.toDTO(servidorTemporario);
    }

    @Transactional
    public void excluir(Long id) {
        servidorTemporarioRepository.deleteById(id);
    }
}