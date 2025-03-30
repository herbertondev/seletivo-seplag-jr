package br.com.projetoseplagjr.dto;

import br.com.projetoseplagjr.model.FotoPessoa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String mae;
    private String pai;
    private List<FotoPessoa> fotos;
    private List<Long> lotacaoIds;
    private List<Long> enderecoIds;
}