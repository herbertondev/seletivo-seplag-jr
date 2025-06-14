package br.com.projetoseplagjr.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPessoaDTO {

    private Long pessoaId;
    @Column(name = "fp_bucket", length = 200)
    private String bucket;

    @Column(name = "fp_hash", length = 200)
    private String hash;
}
