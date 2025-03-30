package br.com.projetoseplagjr.dto;

import br.com.projetoseplagjr.model.FotoPessoa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaResponseDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String mae;
    private String pai;
    private List<FotoPessoa> fotos;
    private List<LotacaoResponseDTO> lotacoes; // Dados completos das lotações
    private List<EnderecoResponseDTO> enderecos; // Dados completos dos endereços
}
