package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LotacaoResponseDTO {

    private Long id;
    private Long pessoaId;
    private Long unidadeId;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;
    private String portaria;
}