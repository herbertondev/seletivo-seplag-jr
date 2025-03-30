package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.ServidorTemporarioDTO;
import br.com.projetoseplagjr.model.ServidorTemporario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ServidorTemporarioMapper {

    @Mapping(target = "pessoaId", source = "pessoa.id")
    ServidorTemporarioDTO toDTO(ServidorTemporario servidorTemporario);

    @Mapping(target = "pessoa.id", source = "pessoaId")
    ServidorTemporario toEntity(ServidorTemporarioDTO servidorTemporarioDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(ServidorTemporarioDTO servidorTemporarioDTO, @MappingTarget ServidorTemporario servidorTemporario);
}