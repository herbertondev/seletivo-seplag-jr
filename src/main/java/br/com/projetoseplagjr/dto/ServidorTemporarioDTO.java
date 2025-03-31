package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServidorTemporarioDTO {

    private Long id;
    private Long pessoaId;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
}