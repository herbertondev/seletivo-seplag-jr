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
    @Mapping(target = "pessoaId", source = "pessoa.id")
    LotacaoResponseDTO toResponseDTO(Lotacao lotacao);


    @Mapping(target = "pessoa.id", source = "pessoaId")
    @Mapping(target = "unidade.id",source = "unidadeId")
    @Mapping(target = "id", ignore = true)
    Lotacao toEntity(LotacaoDTO lotacaoDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", ignore = true) // Impede atualização automática de pessoa
    @Mapping(target = "unidade", ignore = true) // Impede atualização automática de unidade
    void updateEntityFromDTO(LotacaoDTO lotacaoDTO, @MappingTarget Lotacao lotacao);
}