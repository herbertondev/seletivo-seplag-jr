package br.com.projetoseplagjr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Getter
@Setter
public class LotacaoResponseDTO {

    @Schema(example = "1")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(example = "22/05/2023")
    private LocalDate dataLotacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(example = "22/05/2023")
    private LocalDate dataRemocao;
    @Schema(example = "Diretoria")
    private String portaria;
    @Schema(example = "1")
    private Long unidadeId;
    @Schema(example = "1")
    private Long pessoaId;

}