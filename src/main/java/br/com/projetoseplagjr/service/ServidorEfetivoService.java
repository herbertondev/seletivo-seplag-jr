package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.ServidorEfetivoDTO;
import br.com.projetoseplagjr.mappers.ServidorEfetivoMapper;
import br.com.projetoseplagjr.model.ServidorEfetivo;
import br.com.projetoseplagjr.repository.ServidorEfetivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServidorEfetivoService {

    @Inject
    ServidorEfetivoRepository servidorEfetivoRepository;

    @Inject
    ServidorEfetivoMapper servidorEfetivoMapper;

    @Transactional
    public ServidorEfetivoDTO criar(ServidorEfetivoDTO servidorEfetivoDTO) {
        ServidorEfetivo servidorEfetivo = servidorEfetivoMapper.toEntity(servidorEfetivoDTO);
        servidorEfetivoRepository.persist(servidorEfetivo);
        return servidorEfetivoMapper.toDTO(servidorEfetivo);
    }

    public List<ServidorEfetivoDTO> listarTodos() {
        return servidorEfetivoRepository.findAll().stream()
                .map(servidorEfetivoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServidorEfetivoDTO buscarPorId(Long id) {
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.findById(id);
        return servidorEfetivoMapper.toDTO(servidorEfetivo);
    }

    @Transactional
    public ServidorEfetivoDTO atualizar(Long id, ServidorEfetivoDTO servidorEfetivoDTO) {
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.findById(id);
        servidorEfetivoMapper.updateEntityFromDTO(servidorEfetivoDTO, servidorEfetivo);
        servidorEfetivoRepository.persist(servidorEfetivo);
        return servidorEfetivoMapper.toDTO(servidorEfetivo);
    }

}