package br.com.projetoseplagjr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaRequestDTO {

    @Schema(example = "José Augusto")
    private String nome;
    @Schema(example = "25/12/1980")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Schema(example = "M")
    private String sexo;
    @Schema(example = "Maria de Oliveira")
    private String mae;
    @Schema(example = "Mario Augusto")
    private String pai;
    @Schema(example = "1")
    private List<Long> lotacaoIds; // Apenas IDs para associar lotações existentes
    @Schema(example = "1")
    private List<Long> enderecoIds; // Apenas IDs para associar endereços existentes
}
