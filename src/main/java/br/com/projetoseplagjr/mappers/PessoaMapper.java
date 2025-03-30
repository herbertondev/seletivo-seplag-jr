package br.com.projetoseplagjr.mappers;

import br.com.projetoseplagjr.dto.PessoaRequestDTO;
import br.com.projetoseplagjr.dto.PessoaResponseDTO;
import br.com.projetoseplagjr.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = MappingConstants.ComponentModel.JAKARTA_CDI,
        uses = {LotacaoMapper.class, EnderecoMapper.class} // Usa os mappers de Lotacao e Endereco
)
public interface PessoaMapper {

    @Mapping(target = "lotacoes", source = "lotacoes")
    @Mapping(target = "enderecos", source = "enderecos")
    @Mapping(target = "fotos", source = "fotos")
    PessoaResponseDTO toResponseDTO(Pessoa pessoa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lotacoes", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "fotos", ignore = true)
    Pessoa toEntity(PessoaRequestDTO pessoaDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lotacoes", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "fotos", ignore = true)
    void update(@MappingTarget Pessoa pessoa, PessoaRequestDTO pessoaDTO);
}