package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.LotacaoDTO;
import br.com.projetoseplagjr.dto.LotacaoResponseDTO;
import br.com.projetoseplagjr.model.Lotacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface LotacaoMapper {


    @Mapping(target = "unidadeId", source = "unidade.id")
    LotacaoResponseDTO toResponseDTO(Lotacao lotacao);


    Lotacao toEntity(LotacaoDTO lotacaoDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(LotacaoDTO lotacaoDTO, @MappingTarget Lotacao lotacao);
}