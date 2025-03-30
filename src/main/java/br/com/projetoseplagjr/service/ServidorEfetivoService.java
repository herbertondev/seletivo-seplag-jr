package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.ServidorEfetivoDTO;
import br.com.projetoseplagjr.mappers.ServidorEfetivoMapper;
import br.com.projetoseplagjr.model.Pessoa;
import br.com.projetoseplagjr.model.ServidorEfetivo;
import br.com.projetoseplagjr.repository.PessoaRepository;
import br.com.projetoseplagjr.repository.ServidorEfetivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServidorEfetivoService {

    @Inject
    ServidorEfetivoRepository servidorEfetivoRepository;

    @Inject
    ServidorEfetivoMapper servidorEfetivoMapper;

    @Inject
    PessoaRepository pessoaRepository;

    @Transactional
    public ServidorEfetivoDTO criar(ServidorEfetivoDTO servidorEfetivoDTO) {
        if (servidorEfetivoDTO.getPessoaId() == null) {
            throw new IllegalArgumentException("O ID da pessoa é obrigatório");
        }

        // Verifica se já existe um ServidorEfetivo para essa Pessoa
        ServidorEfetivo existente = servidorEfetivoRepository.find("pessoa.id", servidorEfetivoDTO.getPessoaId()).firstResult();
        if (existente != null) {
            throw new WebApplicationException("Já existe um ServidorEfetivo para a Pessoa com ID " + servidorEfetivoDTO.getPessoaId(), 409);
        }

        // Busca a Pessoa associada
        Pessoa pessoa = pessoaRepository.findByIdOptional(servidorEfetivoDTO.getPessoaId())
                .orElseThrow(() -> new NotFoundException("Pessoa com ID " + servidorEfetivoDTO.getPessoaId() + " não encontrada"));

        // Gera a matrícula automaticamente
        String matricula = gerarMatricula(pessoa.getId());

        // Cria a entidade com Pessoa e matrícula
        ServidorEfetivo servidorEfetivo = new ServidorEfetivo(pessoa, matricula);

        // Persiste a entidade
        servidorEfetivoRepository.persist(servidorEfetivo);
        return servidorEfetivoMapper.toDTO(servidorEfetivo);
    }

    public List<ServidorEfetivoDTO> listarTodos() {
        return servidorEfetivoRepository.findAll().stream()
                .map(servidorEfetivoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServidorEfetivoDTO buscarPorId(Long id) {
        // Busca por pes_id (ID da Pessoa) em vez de se_id
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.find("pessoa.id", id).firstResult();
        if (servidorEfetivo == null) {
            throw new NotFoundException("ServidorEfetivo associado à Pessoa com ID " + id + " não encontrado");
        }
        return servidorEfetivoMapper.toDTO(servidorEfetivo);
    }

    @Transactional
    public ServidorEfetivoDTO atualizar(Long id, ServidorEfetivoDTO servidorEfetivoDTO) {
        // Atualiza buscando por se_id
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.findById(id);
        if (servidorEfetivo == null) {
            throw new NotFoundException("ServidorEfetivo com ID " + id + " não encontrado");
        }
        servidorEfetivoMapper.updateEntityFromDTO(servidorEfetivoDTO, servidorEfetivo);
        servidorEfetivoRepository.persist(servidorEfetivo);
        return servidorEfetivoMapper.toDTO(servidorEfetivo);
    }

    private String gerarMatricula(Long pessoaId) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dataHora = agora.format(formatter);
        return dataHora + "-" + pessoaId;
    }
}