package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {

    private String tipoLogradouro;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private Long cidadeId;
}