package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.ServidorEfetivoDTO;
import br.com.projetoseplagjr.model.ServidorEfetivo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ServidorEfetivoMapper {

    @Mapping(target = "pessoaId", source = "pessoa.id")
    ServidorEfetivoDTO toDTO(ServidorEfetivo servidorEfetivo);

    @Mapping(target = "pessoa", ignore = true) // Ignoramos pessoa, será setado no serviço
    @Mapping(target = "matricula", ignore = true) // Ignoramos matrícula, será gerada
    ServidorEfetivo toEntity(ServidorEfetivoDTO servidorEfetivoDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", ignore = true)
    @Mapping(target = "matricula", ignore = true) // Impede atualização da matrícula
    void updateEntityFromDTO(ServidorEfetivoDTO servidorEfetivoDTO, @MappingTarget ServidorEfetivo servidorEfetivo);
}