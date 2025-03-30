package br.com.projetoseplagjr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "foto_pessoa")
@Getter
@Setter
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @Column(name = "pes_id", nullable = false)
    private Long pessoaId;

    @Column(name = "fp_data")
    private LocalDate data;

    @Column(name = "fp_bucket", length = 50)
    private String bucket;

    @Column(name = "fp_hash", length = 64) // Ajustado para SHA-256
    private String hash;
}