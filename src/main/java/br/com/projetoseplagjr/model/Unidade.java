package br.com.projetoseplagjr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unidade")
@Getter
@Setter
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "unid_sigla", length = 20)
    private String sigla;
}