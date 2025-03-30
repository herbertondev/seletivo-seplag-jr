package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LotacaoResponseDTO {
    private Long id;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;
    private String portaria;
    private Long unidadeId; // Inclui o ID da unidade associada
}