package br.com.projetoseplagjr.service;

import br.com.projetoseplagjr.dto.UserRequestDTO;
import br.com.projetoseplagjr.dto.UserResponseDTO;
import br.com.projetoseplagjr.mappers.UserMapper;
import br.com.projetoseplagjr.model.User;
import br.com.projetoseplagjr.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Transactional
    public UserResponseDTO criar(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        userRepository.persist(user);
        return userMapper.toResponseDTO(user);
    }

    public List<UserResponseDTO> listarTodos() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO buscarPorId(Long id) {
        User user = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return userMapper.toResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO atualizar(Long id, UserRequestDTO dto) {
        User user = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        userMapper.updateFromDto(dto, user);
        return userMapper.toResponseDTO(user);
    }

    @Transactional
    public void desativar(Long id) {
        User user = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        user.setActive(false);
    }
}
