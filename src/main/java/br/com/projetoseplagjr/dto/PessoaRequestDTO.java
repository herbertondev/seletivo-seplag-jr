package br.com.projetoseplagjr.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaRequestDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String mae;
    private String pai;
    private List<Long> lotacaoIds; // Apenas IDs para associar lotações existentes
    private List<Long> enderecoIds; // Apenas IDs para associar endereços existentes
}
