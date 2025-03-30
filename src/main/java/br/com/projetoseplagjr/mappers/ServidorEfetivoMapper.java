package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.ServidorEfetivoDTO;
import br.com.projetoseplagjr.model.ServidorEfetivo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ServidorEfetivoMapper {
    ServidorEfetivoDTO toDTO(ServidorEfetivo servidorEfetivo);
    ServidorEfetivo toEntity(ServidorEfetivoDTO servidorEfetivoDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(ServidorEfetivoDTO servidorEfetivoDTO, @MappingTarget ServidorEfetivo servidorEfetivo);
}