package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.EnderecoRequestDTO;
import br.com.projetoseplagjr.dto.EnderecoResponseDTO;
import br.com.projetoseplagjr.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mapping(target = "cidade.id", source = "cidadeId")
    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoRequestDTO request);

    @Mapping(source = "cidade.nome", target = "cidadeNome")
    EnderecoResponseDTO toResponse(Endereco endereco);
}
