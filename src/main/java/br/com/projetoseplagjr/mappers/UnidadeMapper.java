package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.UnidadeDTO;
import br.com.projetoseplagjr.model.Unidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface UnidadeMapper {

    UnidadeDTO toDTO(Unidade unidade);
    Unidade toEntity(UnidadeDTO unidadeDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(UnidadeDTO unidadeDTO, @MappingTarget Unidade unidade);
}