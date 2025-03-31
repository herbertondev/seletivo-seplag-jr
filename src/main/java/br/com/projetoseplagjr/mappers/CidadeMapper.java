package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.CidadeDTO;
import br.com.projetoseplagjr.dto.CidadeRequestDTO;
import br.com.projetoseplagjr.dto.CidadeResponseDTO;
import br.com.projetoseplagjr.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface CidadeMapper {
    CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

    CidadeDTO toDTO(Cidade cidade);
    @Mapping(target = "id", ignore = true)
    Cidade toEntity(CidadeRequestDTO request);
    CidadeResponseDTO toResponse(Cidade cidade);
}
