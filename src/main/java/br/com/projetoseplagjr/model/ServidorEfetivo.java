package br.com.projetoseplagjr.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "servidor_efetivo")
@Getter
public class ServidorEfetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "se_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "pes_id", nullable = false, unique = true) // Garante unicidade de pes_id
    private Pessoa pessoa;

    @Column(name = "se_matricula", length = 20, nullable = false, unique = true) // Matrícula única
    private String matricula;

    // Construtor para criação com matrícula
    public ServidorEfetivo(Pessoa pessoa, String matricula) {
        this.pessoa = pessoa;
        this.matricula = matricula;
    }

    // Construtor vazio exigido pelo JPA
    protected ServidorEfetivo() {
    }

    // Somente getters, sem setter para matricula (imutável)
    public Long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getMatricula() {
        return matricula;
    }
}