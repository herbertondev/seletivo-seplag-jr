package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.UserRequestDTO;
import br.com.projetoseplagjr.dto.UserResponseDTO;
import br.com.projetoseplagjr.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    User toEntity(UserRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    void updateFromDto(UserRequestDTO dto, @MappingTarget User user);
}
